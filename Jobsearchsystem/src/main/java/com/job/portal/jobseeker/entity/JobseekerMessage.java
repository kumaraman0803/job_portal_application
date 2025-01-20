package com.job.portal.jobseeker.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.job.portal.employer.entity.Employer;
/*
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
*/

@Entity
public class JobseekerMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;

    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
   
    
    @ManyToOne
    @JoinColumn(name = "jobseekerid")  // Use the appropriate column name
    private Jobseeker jobseeker;
   
    @ManyToOne
    @JoinColumn(name = "id")
    private Employer employer;

	public JobseekerMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobseekerMessage(Long messageId, String description, Date date, Jobseeker jobseeker, Employer employer) {
		super();
		this.messageId = messageId;
		this.description = description;
		this.date = date;
		this.jobseeker = jobseeker;
		this.employer = employer;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Jobseeker getJobseeker() {
		return jobseeker;
	}

	public void setJobseeker(Jobseeker jobseeker) {
		this.jobseeker = jobseeker;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	@Override
	public String toString() {
		return "JobseekerMessage [messageId=" + messageId + ", description=" + description + ", date=" + date
				+ ", jobseeker=" + jobseeker + ", employer=" + employer + "]";
	}

	
    
    
    
}