package com.nepCafe.asmnttracker.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "ASSIGNMENT")
public class ATResponseDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long atIt;
	@Column
	private String title;
	@Lob
	@Column(length = 20000, name = "DESCRIPTION")	
	private String desc;
	@Column

	private Timestamp expiresOn;
	@Column
	private String status;
	@Lob
	@Column(length = 5000)
	private String link;
	
	@Column
	private String assignmentType;
	@Column
	private int estimatedTimeInHrs;

	@Column
	private Timestamp completedOn;

	@Column
	private int completionTimeInHrs;

	@Column
	private String remarks;

	@ManyToOne(optional = true, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(nullable = true)
	private UserDto updatedBy;

	@Column
	@UpdateTimestamp
	private Timestamp lastUpdatedOn;

	@ManyToOne(optional = true, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(nullable = true)
	private UserDto postedBy;

	@Column
	@CreationTimestamp
	private Timestamp postedOn;

	@Column
	private int priority;

	@ManyToOne(optional = true, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(nullable = true)
	private UserDto techResource;
	
	@ManyToOne(optional = true, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(nullable = true)
	private UserDto assignee;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "atId")
	private Set<AttachmentsDto> attachments;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "atId")
	private List<CommentsDTO> comments;


	public Long getAtIt() {
		return atIt;
	}

	public void setAtIt(Long atIt) {
		this.atIt = atIt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Timestamp getExpiresOn() {
		return expiresOn;
	}

	public void setExpiresOn(Timestamp expiresOn) {
		this.expiresOn = expiresOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getEstimatedTimeInHrs() {
		return estimatedTimeInHrs;
	}

	public void setEstimatedTimeInHrs(int estimatedTimeInHrs) {
		this.estimatedTimeInHrs = estimatedTimeInHrs;
	}

	public Timestamp getCompletedOn() {
		return completedOn;
	}

	public void setCompletedOn(Timestamp completedOn) {
		this.completedOn = completedOn;
	}

	public int getCompletionTimeInHrs() {
		return completionTimeInHrs;
	}

	public void setCompletionTimeInHrs(int completionTimeInHrs) {
		this.completionTimeInHrs = completionTimeInHrs;
	}

	public UserDto getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(UserDto updatedBy) {
		this.updatedBy = updatedBy;
	}

	public UserDto getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(UserDto postedBy) {
		this.postedBy = postedBy;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Set<AttachmentsDto> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<AttachmentsDto> attachments) {
		this.attachments = attachments;
	}

	public Timestamp getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Timestamp lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public Timestamp getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Timestamp postedOn) {
		this.postedOn = postedOn;
	}

	public List<CommentsDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentsDTO> comments) {
		this.comments = comments;
	}

	public UserDto getTechResource() {
		return techResource;
	}

	public void setTechResource(UserDto techResource) {
		this.techResource = techResource;
	}

	public String getAssignmentType() {
		return assignmentType;
	}

	public void setAssignmentType(String assignmentType) {
		this.assignmentType = assignmentType;
	}

	public UserDto getAssignee() {
		return assignee;
	}

	public void setAssignee(UserDto assignee) {
		this.assignee = assignee;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}
