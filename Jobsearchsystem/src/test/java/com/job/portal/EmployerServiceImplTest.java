package com.job.portal;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.job.portal.employer.entity.Employer;
import com.job.portal.employer.repository.EmployerRepository;
import com.job.portal.employer.service.EmployerServiceImpl;



class EmployerServiceImplTest {

    @Mock
    private EmployerRepository employerRepository;

    @InjectMocks
    private EmployerServiceImpl employerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterEmployer() {
        // Setup
        Employer employer = new Employer();
        when(employerRepository.save(employer)).thenReturn(employer);

        // Test
        Employer result = employerService.registerEmployer(employer);

        // Verify
        verify(employerRepository, times(1)).save(employer);
        assertEquals(employer, result);
    }

    @Test
    void testLoginEmployerSuccess() {
        // Setup
        String username = "testUser";
        String password = "testPassword";
        Employer employer = new Employer();
        when(employerRepository.findByUsernameAndPassword(username, password)).thenReturn(Optional.of(employer));

        // Test
        Optional<Employer> result = employerService.loginEmployer(username, password);

        // Verify
        verify(employerRepository, times(1)).findByUsernameAndPassword(username, password);
        assertTrue(result.isPresent());
        assertEquals(employer, result.get());
    }

    private void assertEquals(Employer employer, Employer employer2) {
		// TODO Auto-generated method stub
		
	}

	private void assertTrue(boolean present) {
		// TODO Auto-generated method stub
		
	}

	@Test
    void testLoginEmployerFailure() {
        // Setup
        String username = "testUser";
        String password = "testPassword";
        when(employerRepository.findByUsernameAndPassword(username, password)).thenReturn(Optional.empty());

        // Test
        Optional<Employer> result = employerService.loginEmployer(username, password);

        // Verify
        verify(employerRepository, times(1)).findByUsernameAndPassword(username, password);
        assertTrue(result.isEmpty());
    }
}
