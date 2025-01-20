package com.job.portal.employer.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.job.portal.employer.entity.EmployerMessage;

//import jakarta.transaction.Transactional;





public interface EmployerMessageRepository extends JpaRepository<EmployerMessage, Long>
{
	 List<EmployerMessage> findByEmployerId(Long employerId);

	/* @Modifying
	 @Transactional
	 @Query("INSERT INTO EmployerMessage(em.jobSeeker, em.employer, em.description, em.date) " +
	        "SELECT js, e, :#{#employerMessage.description}, :#{#employerMessage.date} " +
	        "FROM Jobseeker js, Employer e " +
	        "WHERE js.jobseekerid = :jobseekerid AND e.id = :#{#employerMessage.employer.id}")
	 EmployerMessage save(@Param("jobseekerid") Long jobseekerid, @Param("employerMessage") EmployerMessage employerMessage); */
	 
	 
	 
	/* @Modifying
	 @Transactional
	 @Query("INSERT INTO EmployerMessage(description, date, jobseeker, employer) " +
	        "SELECT :#{#employerMessage.description}, :#{#employerMessage.date}, js, e " +
	        "FROM Jobseeker js, Employer e " +
	        "WHERE js.jobseekerid = :jobseekerid AND e.id = :#{#employerMessage.employer.id}")
	 EmployerMessage save(@Param("jobseekerid") Long jobseekerid, @Param("employerMessage") EmployerMessage employerMessage);*/



	
}