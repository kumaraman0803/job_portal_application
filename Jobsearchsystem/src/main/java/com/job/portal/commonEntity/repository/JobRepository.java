package com.job.portal.commonEntity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.job.portal.commonEntity.Job;
//import com.job.portal.employer.entity.Employer;

@EnableJpaRepositories
@Repository
public interface JobRepository extends JpaRepository<Job, Long>{

    @Query("SELECT j FROM Job j WHERE j.location = :location")

	List<Job> findByLocation(String location);

	//List<Job> findAllByUsername(Employer employer);

	List<Job> findBySkill(String skill);

	
}
