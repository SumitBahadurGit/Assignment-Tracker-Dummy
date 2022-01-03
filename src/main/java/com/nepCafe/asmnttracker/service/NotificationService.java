package com.nepCafe.asmnttracker.service;

import com.nepCafe.asmnttracker.http.RemoteNotftnService;
import com.nepCafe.asmnttracker.models.NotificationReponse;
import com.nepCafe.asmnttracker.models.User;
import com.nepCafe.asmnttracker.util.ASTConstants;
import org.nepCafe.kafka.client.contants.NotificationConstants;
import org.nepCafe.kafka.client.models.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

@Service
public class NotificationService {

    private org.nepCafe.kafka.client.config.KProducer notificationProducer;
    private RemoteNotftnService remoteNotftnService;

    @Autowired
    public NotificationService(org.nepCafe.kafka.client.config.KProducer notificationProducer,
                               RemoteNotftnService remoteNotftnService) {
        this.notificationProducer = notificationProducer;
        this.remoteNotftnService = remoteNotftnService;
    }

    public boolean checkExistingNotfn(User user, String action) {

        NotificationReponse existingNotftn = remoteNotftnService.findBuUserIdAndAction(user.getId(), action);

        if (existingNotftn != null && !ObjectUtils.isEmpty(existingNotftn.getNotifications())) {
            Notification notification = existingNotftn.getNotifications().get(0);
            if (notification != null && !notification.isResolved()) {
                System.out.println("Already pending notification found for user " + user.getId() + " for action " + NotificationConstants.PENDING_USER_DOCS);
                return true;
            }
        }
        return false;
    }

    public void generateOnboardingUser(User user) {

        boolean notificationnExists = checkExistingNotfn(user, NotificationConstants.ONBOARDING_CONSULTANT);

        if (!notificationnExists) {
            Notification notification = new Notification();
            notification.setAction(NotificationConstants.ONBOARDING_CONSULTANT);
            notification.setMessage(user.getRole().toUpperCase() + ", " + user.getFirstName() + " " + user.getLastName() + " is onboarding for a future position. Please take required actions.");
            notification.setCreated(new Date());
            notification.setDismissable(true);
            notification.setUserId(String.valueOf(user.getId()));
            notification.setPriority(1);
            notification.setSelfNotification(false);
            notification.setTargetRoles(ASTConstants.SALES);
            notificationProducer.sendMessage("NOTIFICATIONS", notification);
        }

    }

    public void generateTerminatedUser(User user) {

        boolean notificationnExists = checkExistingNotfn(user, NotificationConstants.TERMINATED_CONSULTANT);

        if (!notificationnExists) {
            Notification notification = new Notification();
            notification.setAction(NotificationConstants.TERMINATED_CONSULTANT);
            notification.setMessage(user.getRole().toUpperCase() + ", " + user.getFirstName() + " " + user.getLastName() + " has recently been terminated of his/her position. Please take required actions if needed.");
            notification.setCreated(new Date());
            notification.setDismissable(true);
            notification.setUserId(String.valueOf(user.getId()));
            notification.setPriority(1);
            notification.setSelfNotification(false);
            notification.setTargetRoles(ASTConstants.HR);
            notificationProducer.sendMessage("NOTIFICATIONS", notification);
        }

    }

    public void generateActiveUser(User user) {

        boolean notificationnExists = checkExistingNotfn(user, NotificationConstants.ACTIVE_CONSULTANT);

        if (!notificationnExists) {
            Notification notification = new Notification();
            notification.setAction(NotificationConstants.ACTIVE_CONSULTANT);
            notification.setMessage(user.getRole().toUpperCase() + ", " + user.getFirstName() + " " + user.getLastName() + " has been started joined a new client. Please take required actions if needed.");
            notification.setCreated(new Date());
            notification.setDismissable(true);
            notification.setUserId(String.valueOf(user.getId()));
            notification.setPriority(1);
            notification.setSelfNotification(false);
            notification.setTargetRoles(ASTConstants.HR);
            notificationProducer.sendMessage("NOTIFICATIONS", notification);
        }

    }

    public void generateTrainingUser(User user) {

        boolean notificationnExists = checkExistingNotfn(user, NotificationConstants.TRAINING_CONSULTANT);

        if (!notificationnExists) {
            Notification notification = new Notification();
            notification.setAction(NotificationConstants.TRAINING_CONSULTANT);
            notification.setMessage(user.getRole().toUpperCase() + ", " + user.getFirstName() + " " + user.getLastName() + " has been recently started training. Please take required actions if needed.");
            notification.setCreated(new Date());
            notification.setDismissable(true);
            notification.setUserId(String.valueOf(user.getId()));
            notification.setPriority(1);
            notification.setSelfNotification(false);
            notification.setTargetRoles(ASTConstants.RECRUITER);
            notificationProducer.sendMessage("NOTIFICATIONS", notification);
        }

    }

    public void generateProcessingUser(User user) {

        boolean notificationnExists = checkExistingNotfn(user, NotificationConstants.PROCESSING_CONSULTANT);

        if (!notificationnExists) {
            Notification notification = new Notification();
            notification.setAction(NotificationConstants.PROCESSING_CONSULTANT);
            notification.setMessage(user.getRole().toUpperCase() + ", " + user.getFirstName() + " " + user.getLastName() + " has been recently been added in the system. Please take required actions.");
            notification.setCreated(new Date());
            notification.setDismissable(true);
            notification.setUserId(String.valueOf(user.getId()));
            notification.setPriority(1);
            notification.setSelfNotification(false);
            notification.setTargetRoles(ASTConstants.RECRUITER);
            notificationProducer.sendMessage("NOTIFICATIONS", notification);
        }

    }

    public void generateMarkettingUser(User user) {

        boolean notificationnExists = checkExistingNotfn(user, NotificationConstants.MARKETTING_CONSULTANT);

        if (!notificationnExists) {
            Notification notification = new Notification();
            notification.setAction(NotificationConstants.MARKETTING_CONSULTANT);
            notification.setMessage(user.getRole().toUpperCase() + ", " + user.getFirstName() + " " + user.getLastName() + " has been entered the marketing phase. Please take required actions.");
            notification.setCreated(new Date());
            notification.setDismissable(true);
            notification.setUserId(String.valueOf(user.getId()));
            notification.setPriority(1);
            notification.setSelfNotification(false);
            notification.setTargetRoles(ASTConstants.SALES);
            notificationProducer.sendMessage("NOTIFICATIONS", notification);
        }

    }

    public void generatePendingUserDocsNotfn(User user) {

        boolean notificationnExists = checkExistingNotfn(user, NotificationConstants.PENDING_USER_DOCS);

        if (!notificationnExists) {
            Notification notification = new Notification();
            notification.setAction(NotificationConstants.PENDING_USER_DOCS);
            notification.setMessage("You have pending documents. Please upload the required documents.");
            notification.setCreated(new Date());
            notification.setDismissable(false);
            notification.setUserId(String.valueOf(user.getId()));
            notification.setPriority(1);
            notification.setSelfNotification(false);
            notification.setTargetRoles(ASTConstants.CONSULTANT);
            notificationProducer.sendMessage("NOTIFICATIONS", notification);
        }
    }
}
