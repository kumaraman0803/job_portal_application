package com.job.portal;



import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.job.portal.commonEntity.service.JobService;
import com.job.portal.jobseeker.controller.JobseekerController;
import com.job.portal.jobseeker.entity.Jobseeker;
import com.job.portal.jobseeker.service.JobseekerService;


class JobseekerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private JobseekerService jobSeekerService;

    @Mock
    private JobService jobService;

    @InjectMocks
    private JobseekerController jobseekerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jobseekerController).build();
    }

    @Test
    void testRegisterJobSeeker() throws Exception {
        // Setup
        Jobseeker jobSeeker = new Jobseeker();
        //when(jobSeekerService.registerJobSeeker(jobSeeker)).thenReturn(jobSeeker);

        // Test
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(jobSeeker)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(jobSeeker.getEmail()));

        // Verify
        verify(jobSeekerService, times(1)).registerJobSeeker(jobSeeker);
        verifyNoMoreInteractions(jobSeekerService);
    }

    @Test
    void testLoginJobSeeker() throws Exception {
        // Setup
        String username = "testUser";
        String password = "testPassword";
        Optional<Jobseeker> jobSeeker = Optional.ofNullable(new Jobseeker());
        //when(jobSeekerService.loginJobSeeker(username, password)).thenReturn(jobSeeker);

        // Test
        mockMvc.perform(post("/login")
                .param("username", username)
                .param("password", password))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(jobSeeker.get()));

        // Verify
        verify(jobSeekerService, times(1)).loginJobSeeker(username, password);
        verifyNoMoreInteractions(jobSeekerService);
    }

    @Test
    void testSearchJobSeekersBySkillSet() throws Exception {
        // Setup
        String skillSet = "Java";
        List<Jobseeker> jobSeekers = new ArrayList<>();
        when(jobSeekerService.searchJobSeekersBySkillSet(skillSet)).thenReturn(jobSeekers);

        // Test
        mockMvc.perform(get("/searchBySkillSet")
                .param("skillSet", skillSet))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        // Verify
        verify(jobSeekerService, times(1)).searchJobSeekersBySkillSet(skillSet);
        verifyNoMoreInteractions(jobSeekerService);
    }

    

}

