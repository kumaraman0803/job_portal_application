package com.job.portal.employer.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.job.portal.employer.entity.Employer;





@Service
public interface EmployerService 
{
	Employer registerEmployer(Employer employer);
    Optional<Employer> loginEmployer(String username, String password);
	boolean isUsernameTaken(String username);

}
