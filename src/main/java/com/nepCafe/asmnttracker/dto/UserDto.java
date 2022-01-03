package com.nepCafe.asmnttracker.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.sun.istack.NotNull;

@Entity(name = "USER_")
public class UserDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private Long atId;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	@Column(nullable = true)
	private Date dob;
	@Column(nullable = true)
	private String gender;
	@Column(nullable = false)
	private String email;
	@Column
	private String secondaryEmail;
	@Column(nullable = false)
	private String phone;
	@Column
	private String secondaryPhone;
	@Column(name = "ROLE_TYPE", nullable = false)
	private String role;
	@Column(name = "ACCESS_LEVEL", nullable =  false)
	private String access;	
	@Column(nullable = false)
	private String empStatus;
	@Column
	private String country;
	@Column
	private String immigrationStatus;
	@Column
	private String degree;
	@Column
	private String college;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date addedOn;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date lastUpdated;

	@OneToMany(mappedBy = "assignee", cascade = CascadeType.MERGE)
	private List<ATResponseDTO> assignments;

	@Column
	private String specialities;

	@OneToMany(mappedBy = "comments", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<CommentsDTO> comments;

	@OneToMany(mappedBy = "postedBy", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<ATResponseDTO> posts;

	@OneToMany(mappedBy = "updatedBy", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	private List<ATResponseDTO> updatedBy;
	
	@OneToMany(mappedBy = "addedBy", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)	
	private List<UserDto> addedUsers;

    @NotNull
	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)	
	private UserDto addedBy;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JoinColumn(name = "referredBy", referencedColumnName = "id")
	private UserDto referedBy;

    @NotNull
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)	
	@JoinColumn(name = "lastUpdatedBy", referencedColumnName = "id")
	private UserDto lastUpdatedBy;
	
    @NotNull
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)	
	@JoinColumn(name = "address_id", referencedColumnName = "id")
	private AddressDto address;

    public UserDto() {
    	
    }
	
	public UserDto(Long id, String firstName, String lastName) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public List<CommentsDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentsDTO> comments) {
		this.comments = comments;
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

	public List<ATResponseDTO> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<ATResponseDTO> assignments) {
		this.assignments = assignments;
	}

	public void addAssignment(ATResponseDTO assignment) {
		if (assignments == null) {
			assignments = new ArrayList<>();
		}
		assignments.add(assignment);
	}

	public Long getAtId() {
		return atId;
	}

	public void setAtId(Long atId) {
		this.atId = atId;
	}

	public String getSpecialities() {
		return specialities;
	}

	public void setSpecialities(String specialities) {
		this.specialities = specialities;
	}

	public List<ATResponseDTO> getPosts() {
		return posts;
	}

	public void setPosts(List<ATResponseDTO> posts) {
		this.posts = posts;
	}

	public List<ATResponseDTO> getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(List<ATResponseDTO> updatedBy) {
		this.updatedBy = updatedBy;
	}

	public UserDto getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(UserDto addedBy) {
		this.addedBy = addedBy;
	}

	public List<UserDto> getAddedUsers() {
		return addedUsers;
	}

	public void setAddedUsers(List<UserDto> addedUsers) {
		this.addedUsers = addedUsers;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public UserDto getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(UserDto lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
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

	public String getImmigrationStatus() {
		return immigrationStatus;
	}

	public void setImmigrationStatus(String immigrationStatus) {
		this.immigrationStatus = immigrationStatus;
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

	public UserDto getReferedBy() {
		return referedBy;
	}

	public void setReferedBy(UserDto referedBy) {
		this.referedBy = referedBy;
	}
}
