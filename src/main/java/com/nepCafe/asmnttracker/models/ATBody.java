package com.nepCafe.asmnttracker.models;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ATBody {

    private Long id;
    private String title;
    private String desc;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date expiresOn;
    private String status;
    private String link;
    private int estimatedTimeInHrs;
    private String completedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date completedOn;
    private int completionTimeInHrs;
    private User assignee;
    private User techResource;
    private User updatedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date lastUpdatedOn;
    private User postedBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date postedOn;
    private int priority;
    private List<Attachment> attachments;
    private List<Comment> comments;
    private String assignmentType;
    private String remarks;

    private String message;
    private String errorMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
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

    public String getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(String completedBy) {
        this.completedBy = completedBy;
    }

    public Date getCompletedOn() {
        return completedOn;
    }

    public void setCompletedOn(Date completedOn) {
        this.completedOn = completedOn;
    }

    public int getCompletionTimeInHrs() {
        return completionTimeInHrs;
    }

    public void setCompletionTimeInHrs(int completionTimeInHrs) {
        this.completionTimeInHrs = completionTimeInHrs;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Date getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(Date postedOn) {
        this.postedOn = postedOn;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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

    public String getAssignmentType() {
        return assignmentType;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }


    public User getTechResource() {
        return techResource;
    }

    public void setTechResource(User techResource) {
        this.techResource = techResource;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "ATBody{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", expiresOn=" + expiresOn +
                ", status='" + status + '\'' +
                ", link='" + link + '\'' +
                ", estimatedTimeInHrs=" + estimatedTimeInHrs +
                ", completedBy='" + completedBy + '\'' +
                ", completedOn=" + completedOn +
                ", completionTimeInHrs=" + completionTimeInHrs +
                ", assignee=" + assignee +
                ", techResource=" + techResource +
                ", updatedBy=" + updatedBy +
                ", lastUpdatedOn=" + lastUpdatedOn +
                ", postedBy=" + postedBy +
                ", postedOn=" + postedOn +
                ", priority=" + priority +
                ", attachments=" + attachments +
                ", comments=" + comments +
                ", assignmentType='" + assignmentType + '\'' +
                ", remarks='" + remarks + '\'' +
                ", message='" + message + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
