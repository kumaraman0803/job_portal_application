package com.job.portal.jobseeker.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.job.portal.jobseeker.entity.Jobseeker;



@Repository
public interface JobseekerRepository extends JpaRepository<Jobseeker,Long>
{

	//List<Jobseeker> findJobSeekerById(Long jobseekerid);

	//List<Jobseeker> findBySkillSetContaining(String skillSet);

	//List<Jobseeker> findByJobId(Long jobId);

	//List<Job> findByLocationContaining(String location);

	//Jobseeker findByEmailAndPassword(String username, String password);
	
	
	
	@Query("SELECT j FROM Jobseeker j WHERE j.jobseekerid = ?1")
    List<Jobseeker> findJobSeekerById(Long jobseekerid);

    @Query("SELECT j FROM Jobseeker j WHERE LOWER(j.skillSet) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Jobseeker> findBySkillSetContaining(String skillSet);

    @Query("SELECT j FROM Jobseeker j WHERE j.jobseekerid = ?1")
    List<Jobseeker> findByJobId(Long jobId);

    @Query("SELECT j FROM Jobseeker j WHERE LOWER(j.location) LIKE LOWER(CONCAT('%', ?1, '%'))")
    List<Jobseeker> findByLocationContaining(String location);

    Optional<Jobseeker> findByEmailAndPassword(String username, String password);
    
    @Query("SELECT COUNT(js) > 0 FROM Jobseeker js WHERE js.email = :email AND js.password = :password")
    boolean existsByEmailAndPassword(String email, String password);

    @Query("SELECT j FROM Jobseeker j WHERE LOWER(j.skillSet) LIKE LOWER(CONCAT('%', :skillSet, '%'))")
    List<Jobseeker> searchJobSeekersBySkillSet(String skillSet);
	



}
