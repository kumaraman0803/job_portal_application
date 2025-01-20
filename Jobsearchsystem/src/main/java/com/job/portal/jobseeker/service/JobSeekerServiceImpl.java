package com.job.portal.jobseeker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.job.portal.commonEntity.Job;
import com.job.portal.commonEntity.repository.JobRepository;
import com.job.portal.jobseeker.entity.Jobseeker;
import com.job.portal.jobseeker.repository.JobseekerRepository;




@Service
public class JobSeekerServiceImpl implements JobseekerService {

    @Autowired
    private JobseekerRepository jobseekerrepo;

    @Autowired
    private JobRepository jobRepository; // Inject the JobRepository

    private List<Jobseeker> jobSeekers = new ArrayList<>();

    @Override
    public ResponseEntity<Jobseeker> registerJobSeeker(Jobseeker jobSeeker)
    {
    	Jobseeker savedJobSeeker = jobseekerrepo.save(jobSeeker);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJobSeeker);
    }

    private Long generateUniqueId() {
        // Use a combination of timestamp and a random portion to create a unique ID
        long currentTime = System.currentTimeMillis();
        long randomPart = (long) (Math.random() * 1000); // Adjust the multiplier based on your needs
        return currentTime + randomPart;
    }

    @Override
    public ResponseEntity<Jobseeker> loginJobSeeker(String username, String password) {
        Optional<Jobseeker> jobSeekerOptional = jobseekerrepo.findByEmailAndPassword(username, password);

        if (jobSeekerOptional.isPresent()) {
            // Job seeker found, return ResponseEntity with the job seeker
            return ResponseEntity.ok(jobSeekerOptional.get());
        } else {
            // Job seeker not found, return ResponseEntity with HttpStatus.UNAUTHORIZED
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            // Or, you can return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    private Optional<Jobseeker> getLoggedInJobSeeker(Long jobseekerid) {
       return jobseekerrepo.findById(jobseekerid);
    }

    @Override
    public List<Jobseeker> searchJobSeekersBySkillSet(String skillSet) {
        return jobseekerrepo.findBySkillSetContaining(skillSet);
    }

    @Override
    public List<Jobseeker> searchJobSeekersByJobId(Long jobId) {
        return jobseekerrepo.findByJobId(jobId);
    }

    @Override
    public boolean isUsernameTaken(String name) {
        // TODO: Implement logic to check if username is taken
        return false;
    }

    @Override
    public List<Job> searchJobsByLocation(String location) {
        // Assuming you have a method in the repository to find jobs by location
        return jobRepository.findByLocation(location);
    }
    
}
