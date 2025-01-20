package com.job.portal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.job.portal.commonEntity.Job;
import com.job.portal.commonEntity.repository.JobRepository;
import com.job.portal.commonEntity.service.JobServiceImpl;
import com.job.portal.employer.repository.EmployerRepository;
import com.job.portal.jobseeker.entity.Jobseeker;
import com.job.portal.jobseeker.repository.JobseekerRepository;



class JobServiceImplTest {

    @Mock
    private JobRepository jobRepository;

    @Mock
    private EmployerRepository employerRepository;

    @Mock
    private JobseekerRepository jobSeekerRepository;

    @InjectMocks
    private JobServiceImpl jobService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostJob() {
        // Setup
        Job job = new Job();
        when(jobRepository.save(job)).thenReturn(job);

        // Test
        Job result = jobService.postJob(job);

        // Verify
        verify(jobRepository, times(1)).save(job);
        assertEquals(job, result);
    }

    @Test
    void testEditJobSuccess() {
        // Setup
        Long jobId = 1L;
        Job existingJob = new Job();
        existingJob.setJobid(jobId);
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(existingJob));

        Job updatedJob = new Job();
        updatedJob.setJobTitle("Updated Title");

        // Test
        Job result = jobService.editJob(jobId, updatedJob);

        // Verify
        verify(jobRepository, times(1)).findById(jobId);
        verify(jobRepository, times(1)).save(existingJob);
        assertEquals("Updated Title", result.getJobTitle());
    }

    @Test
    void testEditJobNotFound() {
        // Setup
        Long jobId = 1L;
        when(jobRepository.findById(jobId)).thenReturn(Optional.empty());

        Job updatedJob = new Job();

        // Test and Verify
        assertThrows(NoSuchElementException.class, () -> jobService.editJob(jobId, updatedJob));
        verify(jobRepository, times(1)).findById(jobId);
        verifyNoMoreInteractions(jobRepository);
    }

    // Add similar test methods for other methods in JobServiceImpl

    @Test
    void testApplyForJobSuccess() {
        // Setup
        Long jobId = 1L;
        Long jobSeekerId = 1L;

        Job job = new Job();
        when(jobRepository.findById(jobId)).thenReturn(Optional.of(job));

        Jobseeker jobSeeker = new Jobseeker();
        when(jobSeekerRepository.findById(jobSeekerId)).thenReturn(Optional.of(jobSeeker));

        // Test
        jobService.applyForJob(jobId, jobSeekerId);

        // Verify
        verify(jobRepository, times(1)).findById(jobId);
        verify(jobSeekerRepository, times(1)).findById(jobSeekerId);
        verify(jobSeekerRepository, times(1)).save(jobSeeker);
    }

    @Test
    void testApplyForJobJobNotFound() {
        // Setup
        Long jobId = 1L;
        Long jobSeekerId = 1L;
        when(jobRepository.findById(jobId)).thenReturn(Optional.empty());

        // Test and Verify
        assertThrows(RuntimeException.class, () -> jobService.applyForJob(jobId, jobSeekerId));
        verify(jobRepository, times(1)).findById(jobId);
        verifyNoMoreInteractions(jobSeekerRepository);
    }

    
}
