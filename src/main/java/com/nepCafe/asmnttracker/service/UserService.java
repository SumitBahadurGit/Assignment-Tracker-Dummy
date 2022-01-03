package com.nepCafe.asmnttracker.service;


import com.nepCafe.asmnttracker.dao.UserDao;
import com.nepCafe.asmnttracker.dto.UserDto;
import com.nepCafe.asmnttracker.exceptions.KException;
import com.nepCafe.asmnttracker.exceptions.KExceptionCodes;
import com.nepCafe.asmnttracker.http.RemoteDocService;
import com.nepCafe.asmnttracker.http.RemoteEmployment;
import com.nepCafe.asmnttracker.models.*;
import com.nepCafe.asmnttracker.util.*;
import com.nepCafe.asmnttracker.util.DocConstants.DOCTYPES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserDao userDao;
    private LoginService loginService;
    private MailService mailService;
    private RemoteEmployment remoteEmployment;
    private RemoteDocService remoteDocService;
    private NotificationService notificationService;

    @Autowired
    public UserService(UserDao userDao, LoginService loginService, MailService mailService,
                       RemoteEmployment remoteEmployment, RemoteDocService remoteDocService, NotificationService notificationService) {
        this.userDao = userDao;
        this.loginService = loginService;
        this.mailService = mailService;
        this.remoteEmployment = remoteEmployment;
        this.remoteDocService = remoteDocService;
        this.notificationService = notificationService;
    }

    public void postSaveOrUpdate(User updatedUser) {

        if (!ASTConstants.CONSULTANT.equalsIgnoreCase(updatedUser.getRole())) {
            return;
        }

        if (ASTConstants.PROCESSING.equalsIgnoreCase(updatedUser.getEmpStatus())) {
            notificationService.generateProcessingUser(updatedUser);

        } else if (ASTConstants.TRAINING.equalsIgnoreCase(updatedUser.getEmpStatus())) {
            notificationService.generateTrainingUser(updatedUser);

        } else if (ASTConstants.ACTIVE.equalsIgnoreCase(updatedUser.getEmpStatus())) {
            notificationService.generateActiveUser(updatedUser);

        } else if (ASTConstants.TERMINATED.equalsIgnoreCase(updatedUser.getEmpStatus())) {
            notificationService.generateTerminatedUser(updatedUser);

        } else if (ASTConstants.ONBOARDING.equalsIgnoreCase(updatedUser.getEmpStatus())) {
            notificationService.generateOnboardingUser(updatedUser);

        } else if (ASTConstants.MARKETTING.equalsIgnoreCase(updatedUser.getEmpStatus())) {
            notificationService.generateMarkettingUser(updatedUser);
            boolean isDocsUploaded = remoteDocService.isDocsUploaded(updatedUser.getId());
            if (!isDocsUploaded) {
                notificationService.generatePendingUserDocsNotfn(updatedUser);
            }
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public User update(User user) throws KException {

        if (ObjectUtils.isEmpty(user.getId())) {
            throw new KException(KExceptionCodes.VALIDATION_ERR, "User id is required to update.");
        }

        User response = null;

        // Validate if any new users with existing emails.
        if (!ObjectUtils.isEmpty(user.getEmail())) {

            List<UserDto> existingusersIfAny = userDao.findByEmail(user.getEmail());

            if (!ObjectUtils.isEmpty(existingusersIfAny)) {
                boolean isValid = true;

                if (existingusersIfAny.size() > 1) {
                    isValid = false;

                } else {
                    UserDto exitingUser = existingusersIfAny.get(0);
                    if (!exitingUser.getId().equals(user.getId())) {
                        isValid = false;
                    }
                }
                if (!isValid) {
                    response = new User();
                    response.setErrorMessage("Email is already registered in the system. Please user another email.");
                    return response;
                }
            }
        }

        UserDto existingDto = userDao.find(user.getId());

        if (!ObjectUtils.isEmpty(user.getEmpStatus())) {
            if (!user.getEmpStatus().equalsIgnoreCase(existingDto.getEmpStatus())) {
                // Check if the notification is resolved
                boolean exist = notificationService.checkExistingNotfn(user, NotificationUtil.mapEmpStatusToAction(existingDto.getEmpStatus()));
                if (exist) {
                    response = new User();
                    response.setErrorMessage("There are pending tasks for this user. Please close all the tasks before updating.");
                    return  response;
                }
            }
        }

        existingDto = MapperUtil.map2DtoNonEmpty(existingDto, user);

        if (user.getLastUpdatedBy() != null && user.getLastUpdatedBy().getId() != null) {
            UserDto newLastUpdatedBy = userDao.find(user.getLastUpdatedBy().getId());
            existingDto.setLastUpdatedBy(newLastUpdatedBy);
        }

        existingDto = userDao.save(existingDto);

        if (existingDto != null) {

            response = MapperUtil.dto2User.apply(existingDto);


            if (!ASTConstants.ACTIVE.equals(response.getEmpStatus())) {

                EmploymentResponse employmentResponse = remoteEmployment.getEmployment(response.getId());
                if (employmentResponse != null && !employmentResponse.getEmployments().isEmpty()) {

                    for (Employment activeEmp : employmentResponse.getEmployments()) {
                        if (activeEmp != null) {
                            activeEmp.setStatus(ASTConstants.TERMINATED);
                            remoteEmployment.saveEmployment(activeEmp);
                        }
                    }
                }
            }
            postSaveOrUpdate(response);
        }

        if (response == null) {
            response = new User();
            response.setErrorMessage("Error updating user");
        }
        return response;
    }

    @Transactional(rollbackFor = Exception.class)
    public User save(User user) throws KException {

        User response = null;
        Validator.validateUser(user);

        List<UserDto> existingusersIfAny = userDao.findByEmail(user.getEmail());
        if (!ObjectUtils.isEmpty(existingusersIfAny)) {
            response = new User();
            response.setErrorMessage("Email is already registered in the system. Please user another email.");
            return response;
        }

        if (!ObjectUtils.isEmpty(user.getReferralCode())) {
            try {
                Long userId = Long.parseLong(user.getReferralCode());
                if (userId != 0) {
                    User referredByUser = new User();
                    referredByUser.setId(userId);
                    user.setReferedBy(referredByUser);
                }
            } catch (NumberFormatException nex) {
                throw new KException(0, "Referral code is not valid");
            }
        }

        UserDto dto = userDao.save(MapperUtil.user2Dto.apply(user));

        Login newLogin = new Login();
        if (dto != null) {

            User savedUser = MapperUtil.dto2User.apply(dto);
            String userNamePrefix = savedUser.getRole().toUpperCase().substring(0, 1);
            newLogin.setUserName(userNamePrefix + savedUser.getLastName().toUpperCase());
            newLogin.setPw(generatePassword());
            newLogin.setUser(savedUser);
            this.loginService.save(newLogin);

            if (user.getAddedBy() != null) {
                User addedBy = findUser(user.getAddedBy().getId(), false, false);
                savedUser.setAddedBy(addedBy);
            }
            response = savedUser;
        }

        if (response == null) {
            response = new User();
            response.setErrorMessage("Error saving user");

        } else {
            postSaveOrUpdate(response);
            Email email = MailUtil.handleNewUser(response, newLogin.getUserName(), newLogin.getPw());
            if (email != null) {
                mailService.sendSimpleMessage(email);
            }
        }
        return response;
    }

    public User findUser(Long id, boolean includeDetails, boolean includeAttachments) {
        User response = null;
        UserDto dto = userDao.find(id);
        if (dto != null) {
            response = MapperUtil.dto2User.apply(dto);
        }
        if (response == null) {
            response = new User();
            response.setMessage("No user found");
        } else {
            if (includeDetails) {
                EmploymentResponse employmentResponse = remoteEmployment.getEmployment(id);
                response.setEmploymentResponse(employmentResponse);
            }
            if (includeAttachments) {
                AttachmentResponse attachmentResponse = remoteDocService.findAttachemnts(id,
                        DOCTYPES.USER_DOC.toString());
                response.setAttachmentResponse(attachmentResponse);
            }
        }
        return response;
    }

    public Users searchUsers(String searchTerm) {

        Users response = new Users();
        List<User> usersList = new ArrayList<>();

        if (ObjectUtils.isEmpty(searchTerm)) {
            response.setMessage("Search term cannot be empty");
        } else if (searchTerm.length() < 3) {
            response.setMessage("Search term must be at least 3 characters");
        } else {
            List<UserDto> entities = userDao.searchUsers(searchTerm);
            if (entities != null && !entities.isEmpty()) {
                List<User> responseBody = new ArrayList<>();
                responseBody = entities.stream().map(x -> MapperUtil.dto2User.apply(x)).collect(Collectors.toList());
                response.setUsers(responseBody);
            } else {
                response.setMessage("No users found");
            }
        }

        return response;
    }

    public Users findUsersByRole(String role, String empStatus, int page, int size) {
        Users response = new Users();
        List<User> usersList = new ArrayList<>();

        Page<UserDto> pages = userDao.findUsers(role, empStatus, page, size);

        List<UserDto> entities = pages.getContent();

        if (entities != null && !entities.isEmpty()) {

            List<User> responseBody = new ArrayList<>();
            responseBody = entities.stream().map(x -> MapperUtil.dto2User.apply(x)).collect(Collectors.toList());
            response.setPage(page);
            response.setSize(pages.getNumberOfElements());
            response.setTotalPage(pages.getTotalPages());
            response.setTotalItems(pages.getTotalElements());
            response.setUsers(responseBody);
        } else {
            response.setMessage("No users found");
        }

        return response;
    }

    public List<List<Object>> getActiveEmpStatsByStatus() {


        Map<Object, Object> statusToCount = new LinkedHashMap<>();
        statusToCount.put("STATUS", "COUNT");

        List<Map<Object, Object>> map = userDao.getActiveEmpStatsByStatus();
        if (map != null) {
            map.forEach(status -> {
                if (status.size() > 0) {
                    Object statusName = status.get("STATUS");
                    if (statusName != null) {
                        if (statusName instanceof String) {
                            statusName = statusName.toString().trim();
                        }
                    }

                    Object monthCount = status.get("COUNT");
                    statusToCount.put(statusName, monthCount);
                }
            });
        }

        List<List<Object>> response = statusToCount
                .keySet()
                .stream()
                .map(key -> {
                    return Arrays.asList(key, statusToCount.get(key));
                })
                .collect(Collectors.toList());


        return response;
    }

    private String generatePassword() {

        int length = 8;

        final char[] lowercase = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        final char[] uppercase = "ABCDEFGJKLMNPRSTUVWXYZ".toCharArray();
        final char[] numbers = "0123456789".toCharArray();
        final char[] symbols = "^$?!@#%&".toCharArray();
        final char[] allAllowed = "abcdefghijklmnopqrstuvwxyzABCDEFGJKLMNPRSTUVWXYZ0123456789^$?!@#%&".toCharArray();

        //Use cryptographically secure random number generator
        Random random = new SecureRandom();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length - 4; i++) {
            password.append(allAllowed[random.nextInt(allAllowed.length)]);
        }

        //Ensure password policy is met by inserting required random chars in random positions
        password.insert(random.nextInt(password.length()), lowercase[random.nextInt(lowercase.length)]);
        password.insert(random.nextInt(password.length()), uppercase[random.nextInt(uppercase.length)]);
        password.insert(random.nextInt(password.length()), numbers[random.nextInt(numbers.length)]);
        password.insert(random.nextInt(password.length()), symbols[random.nextInt(symbols.length)]);
        return password.toString();
    }

}
