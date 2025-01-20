package com.job.portal.jobseeker.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.job.portal.jobseeker.entity.JobseekerMessage;



@Repository
public interface JobseekerMessageRepository extends JpaRepository<JobseekerMessage, Long>
{
	
	@Query("SELECT jm FROM JobseekerMessage jm WHERE jm.jobseeker.jobseekerid = :jobseekerid")
    List<JobseekerMessage> findByJobseekerid(@Param("jobseekerid") Long jobseekerid);

    @Query("SELECT jm FROM JobseekerMessage jm WHERE jm.employer.id = :employerId")
    List<JobseekerMessage> findByEmployerId(@Param("employerId") Long employerId);

 //List<JobseekerMessage> findByJobSeekerId(Long jobseekerId);
 //List<JobseekerMessage> findByEmployerId(Long employerId);

 //List<JobseekerMessage> FindJobSeekerMessageById(Long jobId);
 
 
 //List<JobseekerMessage> findJobSeekerMessageById(Long jobseekerid, Long messageId);
 
 @Query("SELECT jm FROM JobseekerMessage jm " +
         "WHERE jm.jobseeker.jobseekerid = :jobseekerid AND jm.messageId = :messageId")
  List<JobseekerMessage> findJobSeekerMessageById(@Param("jobseekerid")Long jobseekerid,  @Param("messageId") Long messageId);

//List<JobseekerMessage> FindJobSeekerMessageById(Long jobId);

//JobseekerMessage sendMessage(JobseekerMessage jobSeekerMessage);


@Query("SELECT jm FROM JobseekerMessage jm WHERE jm.jobseeker.jobseekerid = :jobseekerid")
List<JobseekerMessage> viewSentMessagesByJobSeekerId(@Param("jobseekerid")Long jobseekerid);


@Query("SELECT jm FROM JobseekerMessage jm " +
	       "WHERE jm.jobseeker.jobseekerid = :jobseekerid AND jm.messageId = :messageId")
	List<JobseekerMessage> findById(@Param("jobseekerid") Long jobseekerid, @Param("messageId") Long messageId);




}

