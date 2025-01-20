package com.job.portal.employer.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.job.portal.commonEntity.Job;

/*import com.job.portal.commonEntity.Job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
*/
@Entity
public class Employer 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String organizationName;
    private String address;
    private String contactNo;
    private String email;
    private String username;
    private String password;
    
    @OneToMany(mappedBy = "employer")
    private List<Job> jobs;
	
    public Employer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employer(Long id, String name, String organizationName, String address, String contactNo, String email,
			String username, String password, List<Job> jobs) {
		super();
		this.id = id;
		this.name = name;
		this.organizationName = organizationName;
		this.address = address;
		this.contactNo = contactNo;
		this.email = email;
		this.username = username;
		this.password = password;
		this.jobs = jobs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	@Override
	public String toString() {
		return "Employer [id=" + id + ", name=" + name + ", organizationName=" + organizationName + ", address="
				+ address + ", contactNo=" + contactNo + ", email=" + email + ", username=" + username + ", password="
				+ password + ", jobs=" + jobs + "]";
	}
     
	
	
	
}
