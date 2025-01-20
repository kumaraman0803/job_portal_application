package com.job.portal.commonEntity.service;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.commonEntity.Job;
import com.job.portal.commonEntity.repository.JobRepository;
import com.job.portal.employer.entity.Employer;
import com.job.portal.employer.repository.EmployerRepository;
import com.job.portal.jobseeker.entity.Jobseeker;
import com.job.portal.jobseeker.repository.JobseekerRepository;

//import jakarta.persistence.EntityNotFoundException;






@Service
public class JobServiceImpl implements JobService 
{

	@Autowired
    private JobRepository jobRepository;
    
    @Autowired
    private EmployerRepository employerRepository;
    
    @Autowired
    private JobseekerRepository jobSeekerRepository;
    
    
    private List<Job> jobBasket = new ArrayList<>();
    
    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
	
    @Override
	public Job postJob(Job job)
    {
    	logger.info("Posting a new job: {}", job);
    	return jobRepository.save(job);
	}

	@Override
	public Job editJob(Long jobId, Job updatedJob) 
	{
		logger.info("Editing job with ID: {}", jobId);
		Optional<Job> existingJob = jobRepository.findById(jobId);
        if (existingJob.isPresent()) {
            Job jobToUpdate = existingJob.get();
            jobToUpdate.setJobTitle(updatedJob.getJobTitle());
            jobToUpdate.setLocation(updatedJob.getLocation());
            jobToUpdate.setDescription(updatedJob.getDescription());
            jobToUpdate.setExperience(updatedJob.getExperience());
            jobToUpdate.setSalary(updatedJob.getSalary());
            jobToUpdate.setNoticePeriod(updatedJob.getNoticePeriod());
            jobToUpdate.setContactEmail(updatedJob.getContactEmail());
            jobToUpdate.setStatus(updatedJob.getStatus());

            return jobRepository.save(jobToUpdate);
        } else
        {
        	logger.error("Job not found with ID: {}", jobId);
            throw new NoSuchElementException("Job not found with ID: " + jobId);
        }
	}

	@Override
	public void deleteJob(Long jobId) 
	{
		logger.info("Deleting job with ID: {}", jobId);
		jobRepository.deleteById(jobId);
		
	}

	@Override
	public List<Job> getAllJobsByEmployer(String username)
	{
		logger.info("Getting all jobs for employer with username: {}", username);
		Optional<Employer> employerOptional = employerRepository.findByUsername(username);

        if (employerOptional.isPresent()) {
            Employer employer = employerOptional.get();
            return jobRepository.findAll();
        } else 
        {
        	logger.error("Employer not found with username: {}", username);
            throw new EntityNotFoundException("Employer not found with username: " + username);
        }
	}

	@Override
	public void sendApplicationToEmployer(Job job)
	{
        logger.info("Sending job application to employer for job with ID: {}", job.getJobid());

		jobRepository.save(job);
        System.out.println("Job application sent to employer for job with ID: " + job.getJobid());
	}

	@Override
	public List<Job> searchJobsBySkills(String skills) 
	{
        logger.info("Searching for jobs by skills: {}",skills);
		return jobRepository.findBySkill(skills);
	}

	@Override
	public List<Job> searchJobsByLocation(String location)
	{
        logger.info("Searching for jobs by location: {}", location);

		return jobRepository.findByLocation(location);
	}

	@Override
	public Job getJobDetails(Long jobId) 
	{
        logger.info("Getting details for job with ID: {}", jobId);

		Optional<Job> job = jobRepository.findById(jobId);
        return job.orElse(null);
		
	}

	@Override
	public void applyForJob(Long jobId, Long jobSeekerId) 
	{
		
		Job job = jobRepository.findById(jobId).orElse(null);
		logger.info("Applying logic for job with ID: {}", job.getJobid());
	    Jobseeker jobSeeker = jobSeekerRepository.findById(jobSeekerId).orElse(null);

	    if (job != null && jobSeeker != null) {
	        String appliedJobs = jobSeeker.getAppliedjobs();

	        // Check if the job seeker has already applied for this job
	        if (!containsJobId(appliedJobs, jobId)) {
	            // If the job is not already applied, add it to the applied jobs string
	            appliedJobs = addJobId(appliedJobs, jobId);
	            jobSeeker.setAppliedjobs(appliedJobs);
	            jobSeekerRepository.save(jobSeeker);
	        } else 
	        {
                logger.warn("Job seeker has already applied for job with ID: {}", jobId);

	            throw new RuntimeException("Job seeker has already applied for this job.");
	        }
	    } else
	    {
            logger.error("Job or job seeker not found for applying to job with ID: {}", jobId);

	        throw new RuntimeException("Job or job seeker not found.");
	    }
	}

	// Helper method to check if a job ID exists in the applied jobs string
	private boolean containsJobId(String appliedJobs, Long jobId) {
	    return Arrays.asList(appliedJobs.split(",")).contains(String.valueOf(jobId));
	}

	// Helper method to add a job ID to the applied jobs string
	private String addJobId(String appliedJobs, Long jobId) {
	    if (appliedJobs.isEmpty()) {
	        return String.valueOf(jobId);
	    } else {
	        return appliedJobs + "," + jobId;
	    }
		
		
	}

	
	@Override
	public void addToJobBasket(Job job) 
	{
        logger.info("Adding job to the basket: {}", job);

		if (!jobBasket.contains(job))
		{
	        
	        jobBasket.add(job);
	    } 
		else 
		{
            logger.warn("Job is already in the basket: {}", job);

	        throw new RuntimeException("Job is already in the basket.");
	    }
		
	}

	@Override
	public List<Job> viewJobBasket()
	{
        logger.info("Viewing the job basket");

		List<Job> jobsInBasket = new ArrayList<>();

	    for (Job job : jobBasket) {
	        
	        Job fullJobDetails = jobRepository.findById(job.getJobid()).orElse(null);

	        
	        if (fullJobDetails != null) {
	            jobsInBasket.add(fullJobDetails);
	        }
	    }

	    return jobsInBasket;
	}

	@Override
	public void removeJobFromBasket(Job job) 
	{
        logger.info("Removing job from the basket: {}", job);

		Iterator<Job> iterator = jobBasket.iterator();
	    while (iterator.hasNext()) {
	        Job basketJob = iterator.next();
	        if (basketJob.equals(job)) {
	            iterator.remove();
	            break;
	        }
	    }
		
		
	}

	@Override
	public void applyJobsFromBasket(List<Job> jobs)
	{
        logger.info("Applying jobs from the basket");

		for (Job job : jobs) {
           
            applyForJobLogic(job);

            
            jobBasket.remove(job);
        }
    }


    private void applyForJobLogic(Job job) 
    {
        logger.info("Applying logic for job from the basket with ID: {}", job.getJobid());

        job.setStatus("Applied");
        jobRepository.save(job);

        
       
    }

    }
		

