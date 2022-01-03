package com.nepCafe.asmnttracker.dto;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.nepCafe.asmnttracker.models.User;

@Entity(name = "COMMENTS")
public class CommentsDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private Long atId;

	@Lob
	@Column(length = 20000)
	private String comments;

	@ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	private UserDto postedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date postedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAtId() {
		return atId;
	}

	public void setAtId(Long atId) {
		this.atId = atId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public UserDto getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(UserDto postedBy) {
		this.postedBy = postedBy;
	}

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

}
