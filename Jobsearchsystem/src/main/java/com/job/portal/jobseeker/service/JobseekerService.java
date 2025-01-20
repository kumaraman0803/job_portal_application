package com.job.portal.jobseeker.service;

import java.util.List;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.job.portal.commonEntity.Job;
import com.job.portal.jobseeker.entity.Jobseeker;





public interface JobseekerService 
{
	ResponseEntity<Jobseeker> registerJobSeeker(Jobseeker jobSeeker);
	ResponseEntity<Jobseeker> loginJobSeeker(String username, String password);
	List<Jobseeker> searchJobSeekersBySkillSet(String skillSet);
	List<Jobseeker> searchJobSeekersByJobId(Long jobId);
	List<Job> searchJobsByLocation(String location);
	boolean isUsernameTaken(String name);
}
