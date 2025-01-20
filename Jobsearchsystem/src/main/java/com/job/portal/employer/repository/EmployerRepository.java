package com.job.portal.employer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.job.portal.employer.entity.Employer;



@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

	Optional<Employer> findByUsernameAndPassword(String username, String password);

	Optional<Employer> findByUsername(String username);

	//Employer findByJobsContaining(Job job);

}
