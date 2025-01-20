package com.job.portal.employer.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.job.portal.employer.entity.Employer;
import com.job.portal.employer.repository.EmployerRepository;

@Service
public class EmployerServiceImpl implements EmployerService{

	    @Autowired
	    private EmployerRepository employerRepository;

	    @Override
	    public Employer registerEmployer(Employer employer) {
	        // Additional logic like validation can be added here before saving to the database
	        return employerRepository.save(employer);
	    }

	    @Override
	    public Optional<Employer> loginEmployer(String username, String password) {
	        return employerRepository.findByUsernameAndPassword(username, password);
	    }

		@Override
		public boolean isUsernameTaken(String username) {
			// TODO Auto-generated method stub
			return false;
		}

}
