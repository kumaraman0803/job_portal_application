package com.job.portal.commonEntity.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.job.portal.commonEntity.Job;






public interface JobService 
{

	Job postJob(Job job);
    Job editJob(Long jobId, Job updatedJob);
    void deleteJob(Long jobId);
    List<Job> getAllJobsByEmployer(String username);
    void sendApplicationToEmployer(Job job);
   // Optional<Employer> findByUsername(String username);
    List<Job> searchJobsBySkills(String skills);
    List<Job> searchJobsByLocation(String location);
    Job getJobDetails(Long jobId);
    void applyForJob(Long jobId, Long jobSeekerId);
    void addToJobBasket(Job job);
    List<Job> viewJobBasket();
    void removeJobFromBasket(Job job);
    void applyJobsFromBasket(List<Job> jobs);
	}
