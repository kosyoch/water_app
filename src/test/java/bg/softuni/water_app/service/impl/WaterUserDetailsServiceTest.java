package bg.softuni.water_app.service.impl;

import bg.softuni.water_app.model.entity.User;
import bg.softuni.water_app.model.entity.enums.UserRole;
import bg.softuni.water_app.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WaterUserDetailsServiceTest {

    private WaterUserDetailsService serviceToTest;
    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp(){
        serviceToTest = new WaterUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserNotFound(){
        assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("tosho"));
    }

    @Test
    void testUserFoundException(){
        User testUser = createTestUser();
        when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(testUser);
        UserDetails userDetails = serviceToTest.loadUserByUsername(testUser.getUsername());

        assertNotNull(userDetails);
        assertEquals(testUser.getUsername(), userDetails.getUsername());
        assertEquals(testUser.getPassword(), userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
        assertTrue(containsAuthority(userDetails, "ROLE_" + UserRole.CUSTOMER.name()));
    }

    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority){
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
    }

    private static bg.softuni.water_app.model.entity.User createTestUser(){
        User user = new User();
        user.setUsername("username");
        user.setEmail("test@email.com");
        user.setRole(UserRole.CUSTOMER);
        user.setPassword("password");
        return user;
    }
}
