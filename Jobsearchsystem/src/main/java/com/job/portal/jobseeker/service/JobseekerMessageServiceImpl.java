package com.job.portal.jobseeker.service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.jobseeker.entity.JobseekerMessage;
import com.job.portal.jobseeker.repository.JobseekerMessageRepository;



@Service
public class JobseekerMessageServiceImpl implements JobseekerMessageService {

    @Autowired
    private JobseekerMessageRepository jobSeekerMessageRepository;

    @Override
    public JobseekerMessage sendMessage(JobseekerMessage jobSeekerMessage)
    {
        jobSeekerMessage.setDate(new Date());
        return jobSeekerMessageRepository.save(jobSeekerMessage);
    }

    
    @Override
    public List<JobseekerMessage> viewReceivedMessagesByJobSeekerId(Long jobSeekerId) {
        // Logic to retrieve received messages by Job Seeker
        return jobSeekerMessageRepository.findByEmployerId(jobSeekerId);
    }


	@Override
	public Optional<JobseekerMessage> viewSentMessagesByJobSeekerId(Long jobSeekerId) {
		// TODO Auto-generated method stub
		return jobSeekerMessageRepository.findById(jobSeekerId);
	}


	@Override
	public List<JobseekerMessage> findJobSeekerMessageById(Long jobseekerid, Long messageId) {
		// TODO Auto-generated method stub
		return jobSeekerMessageRepository.findJobSeekerMessageById(jobseekerid, messageId);
	}

    
}
