package com.job.portal.jobseeker.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job.portal.commonEntity.Job;
import com.job.portal.commonEntity.repository.JobRepository;
import com.job.portal.commonEntity.service.JobService;
//import com.job.portal.config.JwtUtil;
import com.job.portal.jobseeker.entity.Jobseeker;
import com.job.portal.jobseeker.entity.JobseekerMessage;
import com.job.portal.jobseeker.repository.JobseekerMessageRepository;
import com.job.portal.jobseeker.repository.JobseekerRepository;
import com.job.portal.jobseeker.service.JobseekerMessageService;
import com.job.portal.jobseeker.service.JobseekerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/jobseekers")
public class JobseekerController 
{

    @Autowired
    private JobseekerService jobSeekerService;
    
    @Autowired 
    private JobRepository jobrepository;
    
    @Autowired
    private JobService jobservice;
    
    @Autowired 
    private  JobseekerMessageService jobseekerMessageService;
  
    //@Autowired
    //private PasswordEncoder passwordEncoder;
    
    @Autowired
    private JobseekerRepository jobSeekerRepository;
    
    @Autowired
    private JobseekerMessageRepository jobSeekerMessageRepository;
    
    
   // @Autowired
   // private JwtUtil jwtUtil;
    
    @GetMapping("/hello")
    public String getHelloMessage() 
    {
        return "Hello, World!";
    }
    
    private static final Logger logger = LoggerFactory.getLogger(JobseekerController.class);

    
    @PostMapping("/register")
    public ResponseEntity<String> registerJobSeeker(@RequestBody Jobseeker jobSeeker) {
        // Check if the username is already taken or other registration validation logic
        if (jobSeekerService.isUsernameTaken(jobSeeker.getName())) {
            logger.warn("Registration failed - Username already taken: {}", jobSeeker.getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken");
        }

        // Hash the password (you should use a secure password hashing method)
        //String hashedPassword = passwordEncoder.encode(jobSeeker.getPassword());
        //jobSeeker.setPassword(hashedPassword);

        // Save the job seeker in the database
        ResponseEntity<Jobseeker> registeredJobSeeker = jobSeekerService.registerJobSeeker(jobSeeker);

        // Log registration information
        logger.info("Registration successful for Job Seeker: {}", jobSeeker.getName());

        // You can return a success message or any relevant response here
        return ResponseEntity.ok("Registration successful");
    }


    /*@PostMapping("/login")
    public ResponseEntity<String> loginJobSeeker(@RequestParam String email, @RequestParam String password) {
        // Validate the username and password
        if (jobSeekerRepository.existsByEmailAndPassword(email, password)) {
            // Login successful
            logger.info("Login successful for Job Seeker: {}", email);
            return ResponseEntity.ok("Login successful");
        } else {
            // Login failed - Invalid credentials
            logger.warn("Login failed - Invalid credentials for Job Seeker: {}", email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }*/
    
    @PostMapping("/login")
    public ResponseEntity<String> loginJobSeeker(@RequestParam String email, @RequestParam String password) {
        Optional<Jobseeker> jobSeekerOptional = jobSeekerRepository.findByEmailAndPassword(email, password);

        if (jobSeekerOptional.isPresent() && jobSeekerOptional.get().getPassword().equals(password)) {
            // Login successful
            logger.info("Login successful for Job Seeker: {}", email);
            return ResponseEntity.ok("Login successful");
        } else {
            // Login failed - Invalid credentials
            logger.warn("Login failed - Invalid credentials for Job Seeker: {}", email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    
    

    @GetMapping("/searchBySkillSet")
    public List<Jobseeker> searchJobSeekersBySkillSet(@RequestParam String skillSet) 
    {
        logger.info("Searching job seekers by skill set: {}", skillSet);

        return jobSeekerService.searchJobSeekersBySkillSet(skillSet);
    }

    @GetMapping("/searchJobsByLocation")
    public List<Job> getJobsByLocation(@RequestParam String location)
    {
        logger.info("Searching jobs by location: {}", location);

        return jobservice.searchJobsByLocation(location);
    }

     
    @GetMapping("/byEmployer/{employerName}")
    public ResponseEntity<List<Job>> getJobsByEmployer(@PathVariable String employerName) 
    {
        logger.info("Getting jobs by employer: {}", employerName);

        List<Job> jobs = jobservice.getAllJobsByEmployer(employerName);

        if (jobs.isEmpty()) 
        {
            logger.info("No jobs found for employer: {}", employerName);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else 
        {
            logger.info("Returning jobs for employer: {}", employerName);

             return new ResponseEntity<>(jobs, HttpStatus.OK);
        }
    }
    
	/*
	 * @GetMapping("/getJobDetails/{jobid}") public ResponseEntity<List<Job>>
	 * getJobDetails(@PathVariable Long jobid) {
	 * logger.info("Getting job details for job ID: {}", jobid);
	 * 
	 * Optional<Job> jobs = jobrepository.findById(jobid); System.out.println(jobs);
	 * return new ResponseEntity<List<Job>>(HttpStatus.OK ); }
	 */
    
    @GetMapping("/getJobDetails/{jobid}")
    public ResponseEntity<Job> getJobDetails(@PathVariable Long jobid) {
        logger.info("Getting job details for job ID: {}", jobid);

        Optional<Job> jobOptional = jobrepository.findById(jobid);

        if (jobOptional.isPresent()) 
        {
            Job job = jobOptional.get();
            return new ResponseEntity<>(job, HttpStatus.OK);
        } else {
            // If job not found, you can return a different HTTP status code, e.g., HttpStatus.NOT_FOUND
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

	/*
	 * @PostMapping("/applyForJob/{jobId}/{jobseekerId}") public
	 * ResponseEntity<Void> applyForJob(@PathVariable Long jobId, @PathVariable Long
	 * jobseekerId) {
	 * logger.info("Applying for job with ID: {} by job seeker with ID: {}", jobId,
	 * jobseekerId);
	 * 
	 * jobservice.applyForJob(jobId, jobseekerId);
	 * 
	 * logger.
	 * info("Application successful for job with ID: {} by job seeker with ID: {}",
	 * jobId, jobseekerId);
	 * 
	 * return new ResponseEntity<>(HttpStatus.OK);
	 * 
	 * }
	 */
    @PostMapping("/applyForJob/{jobId}/{jobseekerId}")
    public ResponseEntity<String> applyForJob(@PathVariable Long jobId, @PathVariable Long jobseekerId) {
        try {
            logger.info("Applying for job with ID: {} by job seeker with ID: {}", jobId, jobseekerId);

            // Your service logic here
            jobservice.applyForJob(jobId, jobseekerId);

            logger.info("Application successful for job with ID: {} by job seeker with ID: {}", jobId, jobseekerId);

            return new ResponseEntity<>("Application successful", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error applying for job with ID: {} by job seeker with ID: {}", jobId, jobseekerId, e);
            return new ResponseEntity<>("Application failed. Please check the details and try again.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    @PostMapping("/send/message")
    public ResponseEntity<JobseekerMessage> sendMessage(@RequestBody JobseekerMessage jobSeekerMessage) {
        JobseekerMessage sentMessage = jobseekerMessageService.sendMessage(jobSeekerMessage);
        return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
    }

   /* @GetMapping("/view/sent/messages/{jobseekerid}")
    public ResponseEntity<List<JobseekerMessage>> viewSentMessages(@RequestParam Long jobseekerid) {
        Optional<JobseekerMessage> sentMessages = jobseekerMessageService.viewSentMessagesByJobSeekerId(jobseekerid);
        return new ResponseEntity<>(sentMessages, HttpStatus.OK);
    }*/
    
    @GetMapping("/view/sent/messages/{jobseekerid}")
    public ResponseEntity<?> viewSentMessages(@PathVariable Long jobseekerid) {
        try {
            Optional<JobseekerMessage> sentMessages = jobseekerMessageService.viewSentMessagesByJobSeekerId(jobseekerid);
            return new ResponseEntity<>(sentMessages, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            // Handle the case where no messages are found for the given jobseekerid
            return new ResponseEntity<>("No messages found for the provided jobseekerid", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Handle other exceptions
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


	/*
	 * @GetMapping("/view/received/messages") public
	 * ResponseEntity<List<JobseekerMessage>> viewReceivedMessages(@RequestParam
	 * Long jobseekerId) { List<JobseekerMessage> receivedMessages =
	 * jobSeekerMessageRepository.viewSentMessagesByJobSeekerId(jobseekerId); return
	 * new ResponseEntity<>(receivedMessages, HttpStatus.OK); }
	 */
    
    @GetMapping("/view/received/messages/{employerId}")
    public ResponseEntity<?> viewReceivedMessages(@PathVariable Long employerId) {
        try {
            List<JobseekerMessage> receivedMessages = jobseekerMessageService.viewReceivedMessagesByJobSeekerId(employerId);
            return new ResponseEntity<>(receivedMessages, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>("No messages found for the provided employerId", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}