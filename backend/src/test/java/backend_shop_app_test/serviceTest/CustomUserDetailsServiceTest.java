package backend_shop_app_test.serviceTest;

import backend_shop_app.dto.UserDTO;
import backend_shop_app.dto.request.AuthRequestDTO;
import backend_shop_app.repository.UserRepository;
import backend_shop_app.service.CustomUserDetailsServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private CustomUserDetailsServiceImpl userDetailsService;

    public CustomUserDetailsServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLoadUserByUsernameWithExistingUser() {
        // Mock data
        UserDTO mockUser = new UserDTO();
        mockUser.setEmail("prova@gmail.com");
        mockUser.setPassword("password");

        when(userRepository.findByEmail("prova@gmail.com")).thenReturn(mockUser);

        // Test the method
        UserDetails userDetails = userDetailsService.loadUserByUsername("prova@gmail.com");

        // Verify the result
        assertNotNull(userDetails);
        assertEquals("prova@gmail.com", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());

        // Verify that the userRepository method was called
        verify(userRepository, times(1)).findByEmail("prova@gmail.com");
    }

}
