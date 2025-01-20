package com.job.portal;

/*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
//import com.job.portal.config.JwtUtil;
import com.job.portal.employer.controller.EmployerController;
import com.job.portal.employer.entity.Employer;
import com.job.portal.employer.service.EmployerService;

@WebMvcTest(EmployerController.class)
@ExtendWith(MockitoExtension.class)
public class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private EmployerService employerService;

    //@Mock
    //private JwtUtil jwtUtil;

    //@Mock
    //private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployerController employerController;

    @Test
    public void testRegisterEmployer() throws Exception {
        // Prepare input data
      //  Employer employer = new Employer(null, "testUsername", "testPassword", null, null, null, null, null);

        // Mock service behavior
        Mockito.when(employerService.isUsernameTaken(employer.getUsername())).thenReturn(false);
       // Mockito.when(passwordEncoder.encode(employer.getPassword())).thenReturn("hashedPassword");
        Mockito.when(employerService.registerEmployer(Mockito.any(Employer.class))).thenReturn(employer);

        // Perform the request
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/employer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(employer)));

        // Verify the response
        resultActions.andExpect(status().isOk());
    }

    @Test
    public void testLoginEmployer() throws Exception {
        // Prepare input data
        String username = "testUsername";
        String password = "testPassword";

        // Mock service behavior
       // Employer employer = new Employer(null, username, password, password, password, password, password, password);
       // Mockito.when(employerService.loginEmployer(username, password)).thenReturn(Optional.of(employer));
        // Commented out JWT-related lines for testing
        // Mockito.when(jwtUtil.generateToken(username)).thenReturn("testToken");

        // Perform the request
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/employer/login")
                .param("username", username)
                .param("password", password));

        // Verify the response
        resultActions.andExpect(status().isOk());
        // Commented out the expectation related to JWT for testing
        // resultActions.andExpect(content().string("testToken"));
    }


}    */
