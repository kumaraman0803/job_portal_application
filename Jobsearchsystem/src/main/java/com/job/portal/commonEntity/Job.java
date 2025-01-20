package com.job.portal.commonEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.job.portal.employer.entity.Employer;

/*import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
*/
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobid;
    private String jobTitle;
    private String location;
    private String description;
    private String experience;
    private String salary;
    private String noticePeriod;
    private String contactEmail;
    private String status;
    private String skill;
    private String Designation;
    private String Company;
    
    @ManyToOne
    @JoinColumn(name = "id")
    private Employer employer;
    
    public Job() {
		// TODO Auto-generated constructor stub
	}

	public Job(Long jobid, String jobTitle, String location, String description, String experience, String salary,
			String noticePeriod, String contactEmail, String status, String skill, String designation, String company,
			Employer employer) {
		super();
		this.jobid = jobid;
		this.jobTitle = jobTitle;
		this.location = location;
		this.description = description;
		this.experience = experience;
		this.salary = salary;
		this.noticePeriod = noticePeriod;
		this.contactEmail = contactEmail;
		this.status = status;
		this.skill = skill;
		Designation = designation;
		Company = company;
		this.employer = employer;
	}
	
	

	public Long getJobid() {
		return jobid;
	}

	public void setJobid(Long jobid) {
		this.jobid = jobid;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getDesignation() {
		return Designation;
	}

	public void setDesignation(String designation) {
		Designation = designation;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public Employer getEmployer() {
		return employer;
	}

	public void setEmployer(Employer employer) {
		this.employer = employer;
	}

	@Override
	public String toString() {
		return "Job [jobid=" + jobid + ", jobTitle=" + jobTitle + ", location=" + location + ", description="
				+ description + ", experience=" + experience + ", salary=" + salary + ", noticePeriod=" + noticePeriod
				+ ", contactEmail=" + contactEmail + ", status=" + status + ", skill=" + skill + ", Designation="
				+ Designation + ", Company=" + Company + ", employer=" + employer + "]";
	}
	
	
    
    
    
}