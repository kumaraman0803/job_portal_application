package com.job.portal.employer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.job.portal.employer.entity.EmployerMessage;



@Service
public interface EmployerMessageService {

 EmployerMessage sendMessageToJobseeker(Long jobseekerid, EmployerMessage employerMessage);

 List<EmployerMessage> viewReceivedMessagesByEmployerId(Long employerId);

 
}

