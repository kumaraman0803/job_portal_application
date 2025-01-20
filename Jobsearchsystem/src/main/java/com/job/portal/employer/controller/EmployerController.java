package com.job.portal.employer.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.job.portal.commonEntity.Job;
import com.job.portal.commonEntity.service.JobService;
//import com.job.portal.config.JwtUtil;
import com.job.portal.employer.entity.Employer;
import com.job.portal.employer.entity.EmployerMessage;
import com.job.portal.employer.repository.EmployerMessageRepository;
import com.job.portal.employer.repository.EmployerRepository;
import com.job.portal.employer.service.EmployerMessageService;
import com.job.portal.employer.service.EmployerService;
import com.job.portal.employer.service.EmployerServiceImpl;
//import com.job.portal.employer.service.EmployerService
import com.job.portal.jobseeker.entity.JobseekerMessage;
import com.job.portal.jobseeker.entity.Jobseeker;
import com.job.portal.jobseeker.entity.JobseekerMessage;
import com.job.portal.jobseeker.repository.JobseekerMessageRepository;
import com.job.portal.jobseeker.repository.JobseekerRepository;
import com.job.portal.jobseeker.service.JobseekerMessageService;
import com.job.portal.jobseeker.service.JobseekerService;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/employer")
public class EmployerController
{
	
	@Autowired
    private EmployerService employerService;
	

    @Autowired
    private EmployerServiceImpl employerservice;
    
    @Autowired
    private EmployerMessageRepository employerMessageRepository;
    
    

    @Autowired
    private EmployerMessageService employermessageservice;
    
    @Autowired
    private EmployerRepository employerRepository;
    
    @Autowired
    private JobService jobservice;
    
    @Autowired
    private JobseekerService jobseekerService;
    
    @Autowired
    private JobseekerRepository jobseekerRepository;
    
    @Autowired
    private JobseekerMessageRepository jobseekerMessageRepository;
    
    @Autowired
    private JobseekerMessageService jobseekerMessageService;
    
    
	
	
    
   
    //@Autowired
    //private JwtUtil jwtUtil;
    
    //@Autowired
    //private PasswordEncoder passwordEncoder;

    
    private static final Logger logger = LoggerFactory.getLogger(EmployerController.class);


    /*@PostMapping("/register")
    public ResponseEntity<String> registerEmployer(@RequestBody Employer employer) {
        // Check if the username is already taken or other registration validation logic
        if (employerService.isUsernameTaken(employer.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken");
        }

        // Hash the password (you should use a secure password hashing method)
        String hashedPassword = passwordEncoder.encode(employer.getPassword());
        employer.setPassword(hashedPassword);

        // Save the employer in the database
        Employer registeredEmployer = employerService.registerEmployer(employer);

        // Generate JWT token for the registered employer
        String token = jwtUtil.generateToken(registeredEmployer.getUsername());
        logger.info("Generated JWT token: {}", token);

        return ResponseEntity.ok(token);
    }*/
    
    @GetMapping("/hello")
    public String getHelloMessage() 
    {
        return "Welcome..!";
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerEmployer(@RequestBody Employer employer) {
        
        if (employerService.isUsernameTaken(employer.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already taken");
        }

        // Hash the password (you should use a secure password hashing method)
        //String hashedPassword = passwordEncoder.encode(employer.getPassword());
        //employer.setPassword(hashedPassword);

        // Save the employer in the database
        Employer registeredEmployer = employerService.registerEmployer(employer);

        // Log registration information
        logger.info("Employer registered: {}", registeredEmployer);

        // You can return a success message or any relevant response here
        return ResponseEntity.ok("Registration successful");
    }


   /* @PostMapping("/login")
    public ResponseEntity<String> loginEmployer(@RequestParam String username, @RequestParam String password) {
        // Validate the username and password
        Optional<Employer> employerOptional = employerService.loginEmployer(username, password);
        
        if (employerOptional.isPresent()) {
             Generate JWT token for the logged-in employer
            String token = jwtUtil.generateToken(username);
            logger.info("Generated JWT token: {}", token);
            
            
            return ResponseEntity.ok();
            
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }*/
    
    @PostMapping("/login")
    public ResponseEntity<String> loginEmployer(@RequestParam String username, @RequestParam String password) {
        // Validate the username and password
        Optional<Employer> employerOptional = employerRepository.findByUsername(username);

        if (employerOptional.isPresent() && employerOptional.get().getPassword().equals(password)) {
            // Login successful
            logger.info("Login successful for Employer: {}", username);
            return ResponseEntity.ok("Login successful");
        } else {
            // Login failed - Invalid credentials
            logger.warn("Login failed - Invalid credentials for Employer: {}", username);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    
    }

    
   @PostMapping("/post/job")
    public ResponseEntity<Job> postJob(@RequestBody Job job) 
    {
        //logger.info("Employer {} posting job: {}", job.getEmployer().getUsername(), job.getJobTitle());

        Job postedJob = jobservice.postJob(job);
        logger.info("Job posted successfully with ID: {}", postedJob.getJobid());

        return new ResponseEntity<>(postedJob, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{jobId}")
    public ResponseEntity<Job> editJob(@PathVariable Long jobId, @RequestBody Job updatedJob) 
    {
        logger.info("Editing job with ID: {}", jobId);

        Job editedJob = jobservice.editJob(jobId, updatedJob);
        logger.info("Job edited successfully with ID: {}", jobId);

        return new ResponseEntity<>(editedJob, HttpStatus.OK);
        
    }
    
    @DeleteMapping("/delete/{jobId}")
    public ResponseEntity<String> deleteJob(@PathVariable Long jobId) 
    {
        logger.info("Deleting job with ID: {}", jobId);

        jobservice.deleteJob(jobId);
        logger.info("Job deleted successfully with ID: {}", jobId);

        return new ResponseEntity<>("Job deleted successfully", HttpStatus.OK);
    }
    
   
    
    @GetMapping("/searchBySkillSet/{skillSet}")
    public ResponseEntity<List<Jobseeker>> searchJobSeekersBySkillSet(@PathVariable String skillSet) {
        List<Jobseeker> jobSeekers = jobseekerRepository.searchJobSeekersBySkillSet(skillSet);
        logger.info("Searching job seekers by skill set: {}", skillSet);


        if (jobSeekers.isEmpty())
        {
            logger.info("No job seekers found for skill set: {}", skillSet);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else 
        {
            logger.info("Job seekers found for skill set: {}", skillSet);

            return new ResponseEntity<>(jobSeekers, HttpStatus.OK);
        }
    }
    
    @GetMapping("/searchByJobId/{jobId}")
    public ResponseEntity<List<Jobseeker>> searchJobSeekersByJobId(@PathVariable Long jobId)
    {
        
        logger.info("Searching job seekers by job ID: {}", jobId);

        List<Jobseeker> jobSeekers = jobseekerRepository.findByJobId(jobId);

        if (jobSeekers.isEmpty()) 
        {
            logger.info("No job seekers found for job ID: {}", jobId);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else 
        {
            logger.info("Job seekers found for job ID: {}", jobId);

            return new ResponseEntity<>(jobSeekers, HttpStatus.OK);
        }
    }

    
    @GetMapping("/receivedByJobseekerid/{jobseekerid}/{messageId}")
    public ResponseEntity<List<JobseekerMessage>> getMessagesReceivedByJobId(@PathVariable Long jobseekerid, Long messageId) {
        List<JobseekerMessage> messages = jobseekerMessageService.findJobSeekerMessageById(jobseekerid, messageId);
        //System.out.println("Jobseekerid /////" +jobseekerid);
        //System.out.println("Messageid /////" +messageId);


        if (messages.isEmpty()) 
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(messages, HttpStatus.OK);
        }
    }
    
    @GetMapping("/sentByJobSeeker/{jobseekerid}/{messageId}")
    public ResponseEntity<List<JobseekerMessage>> getMessagesSentByJobSeeker( @PathVariable Long jobseekerid, @PathVariable Long messageId)
    {
        logger.info("Fetching messages sent by job seeker with ID: {}", jobseekerid);

        List<JobseekerMessage> messages = jobseekerMessageService.findJobSeekerMessageById(jobseekerid, messageId);

       if (messages.isEmpty()) 
        {
            logger.info("No messages found for job seeker with ID: {}", jobseekerid);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else 
        {
            logger.info("Messages found for job seeker with ID: {}", jobseekerid);

            return new ResponseEntity<>(messages, HttpStatus.OK);
        }
    }
    
    
    /*@PostMapping("/sendToShortlistedJobSeeker")
    public ResponseEntity<EmployerMessage> sendMessageToShortlistedJobSeeker(@RequestBody EmployerMessage employerMessage)
    {
        logger.info("Sending message to shortlisted job seeker for job with ID: {}", employerMessage.getJobSeekerId());


        EmployerMessage sentMessage = employermessageservice.sendMessageToJobseeker(employerMessage);
        return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
    }*/
    
    @PostMapping("/sendToShortlistedJobSeeker/{jobseekerid}")
    public ResponseEntity<Object> sendMessageToShortlistedJobSeeker(@PathVariable Long jobseekerid, 
                                                                    @RequestBody EmployerMessage employerMessage) {
        try {
            logger.info("Sending message to shortlisted job seeker for job seeker ID: {}", jobseekerid);

            EmployerMessage sentMessage = employermessageservice.sendMessageToJobseeker(jobseekerid, employerMessage);

            // Assuming employermessageservice.sendMessageToJobseeker returns the sent message

            return new ResponseEntity<>(sentMessage, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            logger.error("Error sending message to shortlisted job seeker", e);

            // You can customize the error message based on the exception or your requirements
            String errorMessage = "Failed to send message to shortlisted job seeker.";
             
            

           // return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    }
    
    


