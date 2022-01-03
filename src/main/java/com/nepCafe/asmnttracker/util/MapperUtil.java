package com.nepCafe.asmnttracker.util;

import com.nepCafe.asmnttracker.dto.*;
import com.nepCafe.asmnttracker.models.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapperUtil {

	public static Function<LogInDto, Login> dto2Login = (req) -> {
		Login resp = new Login();
		resp.setId(req.getId());
		resp.setUserName(req.getUserName());
		resp.setPw(req.getPw());
		if (req.getUser() != null) {
			User user = MapperUtil.dto2User.apply(req.getUser());
			resp.setUser(user);
		}
		return resp;
	};

	public static Function<Login, LogInDto> login2Dto = (req) -> {
		LogInDto resp = new LogInDto();
		resp.setId(req.getId());
		resp.setUserName(req.getUserName());
		resp.setPw(req.getPw());
		return resp;
	};

	public static Function<Address, AddressDto> address2Dto = (req) -> {
		AddressDto resp = new AddressDto();
		resp.setAddressLine1(req.getAddressLine1());
		resp.setAddressLine2(req.getAddressLine2());
		resp.setId(req.getId());
		resp.setCity(req.getCity());
		resp.setState(req.getState());
		resp.setZipCode(req.getZipCode());
		return resp;
	};

	public static Function<AddressDto, Address> dto2Address = (req) -> {

		Address resp = new Address();
		resp.setAddressLine1(req.getAddressLine1());
		resp.setAddressLine2(req.getAddressLine2());
		resp.setId(req.getId());
		resp.setCity(req.getCity());
		resp.setState(req.getState());
		resp.setZipCode(req.getZipCode());
		return resp;
	};

	public static Function<User, UserDto> user2Dto = (req) -> {
		UserDto resp = new UserDto();
		resp.setId(req.getId());
		resp.setFirstName(req.getFirstName());
		resp.setLastName(req.getLastName());
		resp.setDob(req.getDob());
		resp.setGender(req.getGender());
		resp.setRole(req.getRole());
		resp.setAccess(req.getAccess());
		resp.setEmail(req.getEmail());
		resp.setPhone(req.getPhone());
		resp.setEmpStatus(req.getEmpStatus());
		resp.setSecondaryEmail(req.getSecondaryEmail());
		resp.setSecondaryPhone(req.getSecondaryPhone());
		resp.setCollege(req.getCollege());
		resp.setDegree(req.getDegree());
		resp.setCountry(req.getCountry());
		resp.setImmigrationStatus(req.getImmigrationstatus());
		
		
		if (req.getAddress() != null) {
			resp.setAddress(address2Dto.apply(req.getAddress()));
		}
		if (req.getSpecialities() != null && !req.getSpecialities().isEmpty()) {
			resp.setSpecialities(req.getSpecialities().toString().replaceAll(",", ""));
		}

		if (req.getLastUpdatedBy() != null) {
			UserDto lastUpdatedBy = new UserDto();
			lastUpdatedBy.setId(req.getLastUpdatedBy().getId());
			resp.setAddedBy(lastUpdatedBy);
		}

		if (req.getAddedBy() != null) {
			UserDto addedBy = new UserDto();
			addedBy.setId(req.getAddedBy().getId());
			resp.setAddedBy(addedBy);
		}

		if (req.getReferedBy() != null) {
			UserDto referredByDto = new UserDto();
			referredByDto.setId(req.getReferedBy().getId());
			resp.setReferedBy(referredByDto);
		}

		return resp;
	};

	public static Function<UserDto, User> dto2User = (req) -> {
		User resp = new User();
		resp.setId(req.getId());
		resp.setFirstName(req.getFirstName());
		resp.setLastName(req.getLastName());
		resp.setDob(req.getDob());
		resp.setGender(req.getGender());
		resp.setRole(req.getRole());
		resp.setAccess(req.getAccess());
		resp.setEmail(req.getEmail());
		resp.setLastUpdated(req.getLastUpdated());
		resp.setAddedOn(req.getAddedOn());
		resp.addSpecialities(req.getSpecialities());
		resp.setPhone(req.getPhone());
		resp.setEmpStatus(req.getEmpStatus());
		resp.setCountry(req.getCountry());
		resp.setSecondaryEmail(req.getSecondaryEmail());
		resp.setSecondaryPhone(req.getSecondaryPhone());
		resp.setCollege(req.getCollege());
		resp.setDegree(req.getDegree());
		resp.setImmigrationstatus(req.getImmigrationStatus());
		
		if (req.getAddress() != null) {
			resp.setAddress(dto2Address.apply(req.getAddress()));
		}

		if (req.getLastUpdatedBy() != null) {
			User user = new User();
			user.setId(req.getLastUpdatedBy().getId());
			user.setFirstName(req.getLastUpdatedBy().getFirstName());
			user.setLastName(req.getLastUpdatedBy().getLastName());
			resp.setLastUpdatedBy(user);
		}

		if (req.getAddedBy() != null) {
			User user = new User();
			user.setId(req.getAddedBy().getId());
			user.setFirstName(req.getAddedBy().getFirstName());
			user.setLastName(req.getAddedBy().getLastName());
			resp.setAddedBy(user);
		}

		if (req.getReferedBy() != null) {
			User referredBy = new User();
			referredBy.setId(req.getReferedBy().getId());
			resp.setReferedBy(referredBy);
		}


		return resp;
	};

	public static Function<UserDto, User> dto2UserMin = (req) -> {
		User resp = new User();
		resp.setId(req.getId());
		resp.setFirstName(req.getFirstName());
		resp.setLastName(req.getLastName());
		resp.setDob(req.getDob());
		resp.setGender(req.getGender());
		resp.setRole(req.getRole());
		resp.setAccess(req.getAccess());
		resp.setEmail(req.getEmail());
		resp.setLastUpdated(req.getLastUpdated());
		resp.setAddedOn(req.getAddedOn());
		resp.addSpecialities(req.getSpecialities());
		resp.setPhone(req.getPhone());
		resp.setEmpStatus(req.getEmpStatus());
		resp.setCountry(req.getCountry());
		resp.setSecondaryEmail(req.getSecondaryEmail());
		resp.setSecondaryPhone(req.getSecondaryPhone());
		resp.setCollege(req.getCollege());
		resp.setDegree(req.getDegree());
		resp.setImmigrationstatus(req.getImmigrationStatus());
		return resp;
	};
	public static Function<CommentsDTO, Comment> dto2Comment = (req) -> {
		Comment resp = new Comment();
		resp.setId(req.getId());
		resp.setComments(req.getComments());
		resp.setAtId(req.getAtId());
		User user = dto2User.apply(req.getPostedBy());
		resp.setAuthor(user);
		resp.setPostedOn(req.getPostedOn());
		return resp;
	};

	public static Function<Comment, CommentsDTO> comment2Dto = (req) -> {
		CommentsDTO resp = new CommentsDTO();
		resp.setId(req.getId());
		resp.setComments(req.getComments());
		resp.setAtId(req.getAtId());
		UserDto user = new UserDto();
		user.setId(req.getAuthor().getId());
		resp.setPostedBy(user);
		resp.setPostedOn(req.getPostedOn());
		return resp;
	};

	public static Function<AttachmentsDto, Attachment> dto2Attachment = (req) -> {
		Attachment attachment = new Attachment();
		attachment.setId(req.getId());
		attachment.setAtId(req.getAtId());
		attachment.setLink(req.getLink());
		attachment.setLastUpdated(req.getLastUpdated());
		return attachment;
	};

	public static Function<ATBody, ATResponseDTO> atResp2DtoFunc = (req) -> {
		ATResponseDTO resp = new ATResponseDTO();
		resp.setAtIt(req.getId());
		if (req.getCompletedOn() != null) {
			resp.setCompletedOn(new Timestamp(req.getCompletedOn().getTime()));
		}
		resp.setCompletionTimeInHrs(req.getCompletionTimeInHrs());
		resp.setDesc(req.getDesc());
		resp.setRemarks(req.getRemarks());
		resp.setEstimatedTimeInHrs(req.getEstimatedTimeInHrs());
		if (req.getExpiresOn() != null) {
			resp.setExpiresOn(new Timestamp(req.getExpiresOn().getTime()));
		}

		if (req.getPostedBy() != null && req.getPostedBy().getId() != 0) {
			UserDto user = new UserDto();
			user.setId(req.getPostedBy().getId());
			resp.setPostedBy(user);
		}	
		resp.setAssignmentType(req.getAssignmentType());
		resp.setPriority(req.getPriority());
		resp.setStatus(req.getStatus());
		resp.setTitle(req.getTitle());
		if (req.getUpdatedBy() != null && req.getUpdatedBy().getId() != 0) {
			UserDto user = new UserDto();
			user.setId(req.getPostedBy().getId());
			resp.setUpdatedBy(user);
		}
		resp.setLink(req.getLink());
		if (req.getTechResource() != null && req.getTechResource().getId() != 0) {
			resp.setTechResource(user2Dto.apply(req.getTechResource()));
		}
		if (req.getAssignee() != null && req.getAssignee().getId() != 0) {
			resp.setAssignee(user2Dto.apply(req.getAssignee()));
		}
		if (req.getLastUpdatedOn() != null) {
			resp.setLastUpdatedOn(new Timestamp(req.getLastUpdatedOn().getTime()));
		}

		if (req.getPostedOn() != null) {
			resp.setPostedOn(new Timestamp(req.getPostedOn().getTime()));
		}

		return resp;
	};

	public static Function<ATResponseDTO, ATBody> dto2atRespFunc = (req) -> {
		ATBody resp = new ATBody();
		resp.setId(req.getAtIt());
		resp.setCompletedOn(req.getCompletedOn());
		resp.setCompletionTimeInHrs(req.getCompletionTimeInHrs());
		resp.setDesc(req.getDesc());
		resp.setEstimatedTimeInHrs(req.getEstimatedTimeInHrs());
		resp.setExpiresOn(req.getExpiresOn());
		resp.setRemarks(req.getRemarks());
		if (req.getPostedBy() != null) {
			User user = dto2User.apply(req.getPostedBy());
			resp.setPostedBy(user);
		}

		resp.setAssignmentType(req.getAssignmentType());
		resp.setPriority(req.getPriority());
		resp.setStatus(req.getStatus());
		resp.setTitle(req.getTitle());
		if (req.getUpdatedBy() != null) {
			User user = dto2User.apply(req.getUpdatedBy());
			resp.setUpdatedBy(user);
		}

		resp.setLink(req.getLink());
		if (req.getTechResource() != null) {
			resp.setTechResource(dto2User.apply(req.getTechResource()));
		}
		if (req.getAssignee() != null) {
			resp.setAssignee(dto2User.apply(req.getAssignee()));
		}
		resp.setLastUpdatedOn(req.getLastUpdatedOn());
		resp.setPostedOn(req.getPostedOn());
		Set<AttachmentsDto> dtos = req.getAttachments();
		if (dtos != null) {
			List<Attachment> attachments = dtos.stream().map(x -> dto2Attachment.apply(x)).collect(Collectors.toList());
			resp.setAttachments(attachments);
		}

		List<CommentsDTO> commentDtos = req.getComments();
		if (commentDtos != null) {
			List<Comment> comments = commentDtos.stream().map(x -> dto2Comment.apply(x)).collect(Collectors.toList());
			resp.setComments(comments);
		}

		return resp;
	};

	public static Function<ATResponseDTO, ATBody> dto2MinAtRespFunc = (req) -> {

		ATBody resp = new ATBody();
		resp.setId(req.getAtIt());
		resp.setCompletedOn(req.getCompletedOn());
		resp.setCompletionTimeInHrs(req.getCompletionTimeInHrs());
		resp.setDesc(req.getDesc());
		resp.setEstimatedTimeInHrs(req.getEstimatedTimeInHrs());
		resp.setExpiresOn(req.getExpiresOn());
		resp.setRemarks(req.getRemarks());
		if (req.getPostedBy() != null) {
			resp.setPostedBy(dto2UserMin.apply(req.getPostedBy()));
		}

		resp.setAssignmentType(req.getAssignmentType());
		resp.setPriority(req.getPriority());
		resp.setStatus(req.getStatus());
		resp.setTitle(req.getTitle());
		if (req.getUpdatedBy() != null) {
			resp.setUpdatedBy(dto2UserMin.apply(req.getUpdatedBy()));
		}

		resp.setLink(req.getLink());
		if (req.getTechResource() != null) {
			resp.setTechResource(dto2UserMin.apply(req.getTechResource()));
		}
		if (req.getAssignee() != null) {
			resp.setAssignee(dto2UserMin.apply(req.getAssignee()));
		}
		resp.setLastUpdatedOn(req.getLastUpdatedOn());
		resp.setPostedOn(req.getPostedOn());

		return resp;
	};

	public static AddressDto map2DtoNotEmpty(AddressDto resp, Address req) {
		resp.setAddressLine1(req.getAddressLine1() != null ? req.getAddressLine1() : resp.getAddressLine1());
		resp.setAddressLine2(req.getAddressLine2() != null ? req.getAddressLine2() : resp.getAddressLine2());
		resp.setCity(req.getCity() != null ? req.getCity() : resp.getCity());
		resp.setState(req.getState() != null ? req.getState() : resp.getState());
		resp.setZipCode(req.getZipCode() != null ? req.getZipCode() : resp.getZipCode());
		return resp;

	}

	public static ATResponseDTO map2DtoNotEmpty(ATResponseDTO resp, ATBody req) {

		resp.setCompletedOn(
				req.getCompletedOn() != null ? new Timestamp(resp.getCompletedOn().getTime()) : resp.getCompletedOn());
		resp.setCompletionTimeInHrs(
				req.getCompletionTimeInHrs() != 0 ? req.getCompletionTimeInHrs() : resp.getCompletionTimeInHrs());
		resp.setDesc(req.getDesc() != null ? req.getDesc() : resp.getDesc());
		resp.setEstimatedTimeInHrs(
				req.getEstimatedTimeInHrs() != 0 ? req.getEstimatedTimeInHrs() : resp.getEstimatedTimeInHrs());
		resp.setExpiresOn(
				req.getExpiresOn() != null ? new Timestamp(req.getExpiresOn().getTime()) : resp.getExpiresOn());
		resp.setPriority(req.getPriority() != 0 ? req.getPriority() : resp.getPriority());
		resp.setStatus(req.getStatus() != null ? req.getStatus() : resp.getStatus());
		resp.setTitle(req.getTitle() != null ? req.getTitle() : resp.getTitle());
		resp.setAssignmentType(req.getAssignmentType() != null ? req.getAssignmentType() : resp.getAssignmentType() );
		resp.setLink(req.getLink() != null ? req.getLink() : resp.getLink());
		resp.setRemarks(req.getRemarks() != null ? req.getRemarks() : resp.getRemarks());
		return resp;
	};

	public static UserDto map2DtoNonEmpty(UserDto resp, User req) {
		
		resp.setAccess(req.getAccess() != null ? req.getAccess() : resp.getAccess());
		resp.setFirstName(req.getFirstName() != null ? req.getFirstName() : resp.getFirstName());
		resp.setLastName(req.getLastName() != null ? req.getLastName() : resp.getLastName());
		resp.setDob(req.getDob() != null ? req.getDob() : resp.getDob());
		resp.setGender(req.getGender() != null ? req.getGender() : resp.getGender());		
		resp.setRole(req.getRole() != null ? req.getRole() : resp.getRole());
		resp.setAccess(req.getAccess() != null ? req.getAccess() : resp.getAccess());
		resp.setEmail(req.getEmail() != null ? req.getEmail() : resp.getEmail());
		resp.setSecondaryEmail(req.getSecondaryEmail() != null ? req.getSecondaryEmail() : resp.getSecondaryEmail());
		resp.setPhone(req.getPhone() != null ? req.getPhone() : resp.getPhone());
		resp.setSecondaryPhone(req.getSecondaryPhone() != null ? req.getSecondaryPhone() : resp.getSecondaryPhone());
		resp.setImmigrationStatus(req.getImmigrationstatus() != null ? req.getImmigrationstatus() : resp.getImmigrationStatus());
		resp.setCountry(req.getCountry() != null ?  req.getCountry() : resp.getCountry());
		resp.setCollege(req.getCollege() != null ? req.getCollege() : resp.getCollege());
		resp.setDegree(req.getDegree() != null ? req.getDegree() : resp.getDegree());
		resp.setEmpStatus(req.getEmpStatus() != null ? req.getEmpStatus(): resp.getEmpStatus());
		if (req.getAddress() != null && req.getAddress().getId() != null) {
			if (req.getAddress() != null) {
				resp.setAddress(map2DtoNotEmpty(resp.getAddress(), req.getAddress()));
			} else {
				resp.setAddress(map2DtoNotEmpty(new AddressDto(), req.getAddress()));
			}
		}

		return resp;
	}
}
