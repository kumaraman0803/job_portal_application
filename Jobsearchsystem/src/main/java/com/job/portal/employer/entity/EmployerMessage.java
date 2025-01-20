package com.job.portal.employer.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.job.portal.jobseeker.entity.Jobseeker;
/*
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
*/

@Entity
public class EmployerMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;

    private String description;
    //private Long jobSeekerId;
    //private Long employerId;
    private LocalDateTime date;
   
    @ManyToOne
    @JoinColumn(name = "jobseekerid")  // Use the appropriate column name
    private Jobseeker jobseeker;

    @ManyToOne
    @JoinColumn(name = "id")  // Use the appropriate column name
    private Employer employer;
    
	public EmployerMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployerMessage(Long messageId, String description, LocalDateTime date, Jobseeker jobSeeker,
			Employer employer) {
		super();
		this.messageId = messageId;
		this.description = description;
		this.date = date;
		this.jobseeker = jobSeeker;
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

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Jobseeker getJobSeeker() {
		return jobseeker;
	}

	public void setJobSeeker(Jobseeker jobSeeker) {
		this.jobseeker = jobSeeker;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	@Override
	public String toString() {
		return "EmployerMessage [messageId=" + messageId + ", description=" + description + ", date=" + date
				+ ", jobSeeker=" + jobseeker + ", employer=" + employer + "]";
	}

	
	
	
}
