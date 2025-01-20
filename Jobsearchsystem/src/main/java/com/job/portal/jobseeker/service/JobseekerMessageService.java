package com.job.portal.jobseeker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.job.portal.jobseeker.entity.JobseekerMessage;




public interface JobseekerMessageService
{

 JobseekerMessage sendMessage(JobseekerMessage jobSeekerMessage);

 Optional<JobseekerMessage> viewSentMessagesByJobSeekerId(Long jobSeekerId);

 List<JobseekerMessage> viewReceivedMessagesByJobSeekerId(Long jobSeekerId);
 List<JobseekerMessage> findJobSeekerMessageById(Long jobseekerid, Long messageId);

}
