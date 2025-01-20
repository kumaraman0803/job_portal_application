package com.job.portal.jobseeker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
*/
@Entity
public class Jobseeker {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jobseekerid;
    
    private String name;
    private String password;
    private String address;
    private String contactNo;
    private String email;
    private String skillSet;
    private String appliedjobs;
    private String location;
    
    //@OneToMany(mappedBy = "jobseeker")
    //private List<JobseekerMessage> sentMessages;
    
    
	
    public Jobseeker() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Jobseeker(Long jobseekerid, String name, String password, String address, String contactNo, String email,
			String skillSet, String appliedjobs, String location) {
		super();
		this.jobseekerid = jobseekerid;
		this.name = name;
		this.password = password;
		this.address = address;
		this.contactNo = contactNo;
		this.email = email;
		this.skillSet = skillSet;
		this.appliedjobs = appliedjobs;
		this.location = location;
	}

	public Long getJobseekerid() {
		return jobseekerid;
	}

	public void setJobseekerid(Long jobseekerid) {
		this.jobseekerid = jobseekerid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSkillSet() {
		return skillSet;
	}

	public void setSkillSet(String skillSet) {
		this.skillSet = skillSet;
	}

	public String getAppliedjobs() {
		return appliedjobs;
	}

	public void setAppliedjobs(String appliedjobs) {
		this.appliedjobs = appliedjobs;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Jobseeker [jobseekerid=" + jobseekerid + ", name=" + name + ", password=" + password + ", address="
				+ address + ", contactNo=" + contactNo + ", email=" + email + ", skillSet=" + skillSet
				+ ", appliedjobs=" + appliedjobs + ", location=" + location + "]";
	}

	


    
}

