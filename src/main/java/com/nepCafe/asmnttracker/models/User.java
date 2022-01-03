package com.nepCafe.asmnttracker.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.nepCafe.asmnttracker.repo.AttachmentsRepo;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

	private Long id;
	private String firstName;
	private String lastName;
	private Date dob;
	private String gender;
	private String email;
	private String secondaryEmail;
	private String phone;
	private String secondaryPhone;
	private String referralCode;
	private String role;
	private String access;
	private String empStatus;
	private String immigrationstatus;
	private String country;
	private String degree;
	private String college;
	private User addedBy;
	private User lastUpdatedBy;
	private User referedBy;
	private Date addedOn;
	private Date lastUpdated;
	private String message;
	private String errorMessage;
	private EmploymentResponse employmentResponse;
	private AttachmentResponse attachmentResponse;
	private List<String> specialities;
	private Address address;
	
	public User() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(User addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<String> getSpecialities() {
		return specialities;
	}

	public void setSpecialities(List<String> specialities) {
		this.specialities = specialities;
	}

	
	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}
	

	public String getEmpStatus() {
		return empStatus;
	}


	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}


	public String getAccess() {
		return access;
	}


	public void setAccess(String access) {
		this.access = access;
	}
	

	public User getLastUpdatedBy() {
		return lastUpdatedBy;
	}


	public void setLastUpdatedBy(User lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	

	public EmploymentResponse getEmploymentResponse() {
		return employmentResponse;
	}


	public void setEmploymentResponse(EmploymentResponse employmentResponse) {
		this.employmentResponse = employmentResponse;
	}


	
	public AttachmentResponse getAttachmentResponse() {
		return attachmentResponse;
	}


	public void setAttachmentResponse(AttachmentResponse attachmentResponse) {
		this.attachmentResponse = attachmentResponse;
	}

	

	public String getSecondaryEmail() {
		return secondaryEmail;
	}


	public void setSecondaryEmail(String secondaryEmail) {
		this.secondaryEmail = secondaryEmail;
	}


	public String getSecondaryPhone() {
		return secondaryPhone;
	}


	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}




	public String getDegree() {
		return degree;
	}


	public void setDegree(String degree) {
		this.degree = degree;
	}


	public String getCollege() {
		return college;
	}


	public void setCollege(String college) {
		this.college = college;
	}

	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}

	

	public String getImmigrationstatus() {
		return immigrationstatus;
	}


	public void setImmigrationstatus(String immigrationstatus) {
		this.immigrationstatus = immigrationstatus;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}

	public User getReferedBy() {
		return referedBy;
	}

	public void setReferedBy(User referedBy) {
		this.referedBy = referedBy;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public void addSpecialities(String specialities) {
		if (this.specialities == null)
			this.specialities = new ArrayList<>();

		if (specialities != null && !specialities.isEmpty()) {
			List<String> temp = Arrays.asList(specialities.split(" "));
			this.specialities.addAll(temp);
		}

	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", dob=" + dob +
				", gender='" + gender + '\'' +
				", email='" + email + '\'' +
				", secondaryEmail='" + secondaryEmail + '\'' +
				", phone='" + phone + '\'' +
				", secondaryPhone='" + secondaryPhone + '\'' +
				", referralCode='" + referralCode + '\'' +
				", role='" + role + '\'' +
				", access='" + access + '\'' +
				", empStatus='" + empStatus + '\'' +
				", immigrationstatus='" + immigrationstatus + '\'' +
				", country='" + country + '\'' +
				", degree='" + degree + '\'' +
				", college='" + college + '\'' +
				", addedBy=" + addedBy +
				", lastUpdatedBy=" + lastUpdatedBy +
				", referedBy=" + referedBy +
				", addedOn=" + addedOn +
				", lastUpdated=" + lastUpdated +
				", message='" + message + '\'' +
				", errorMessage='" + errorMessage + '\'' +
				", employmentResponse=" + employmentResponse +
				", attachmentResponse=" + attachmentResponse +
				", specialities=" + specialities +
				", address=" + address +
				'}';
	}
}
