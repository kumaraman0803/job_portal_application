package com.job.portal.employer.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.employer.entity.EmployerMessage;
import com.job.portal.employer.repository.EmployerMessageRepository;



@Service
public class EmployerMessageServiceImpl implements EmployerMessageService {

 @Autowired
 private EmployerMessageRepository employerMessageRepository;

@Override
public EmployerMessage sendMessageToJobseeker(Long jobseekerid, EmployerMessage employerMessage) {
	employerMessage.setDate(LocalDateTime.now());
    //return employerMessageRepository.save(jobseekerid, employerMessage);
    return employerMessageRepository.save(employerMessage);
}

@Override
public List<EmployerMessage> viewReceivedMessagesByEmployerId(Long employerId) 
{
	return employerMessageRepository.findByEmployerId(employerId);
}


}
 


