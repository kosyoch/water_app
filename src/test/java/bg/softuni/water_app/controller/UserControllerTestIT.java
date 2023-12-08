package bg.softuni.water_app.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testRegistration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .param("username","user")
                .param("email", "email@email.com")
                .param("password", "test")
                .param("confirmPassword", "test")
                .param("role", "CUSTOMER")
                .with(csrf())

        ).andExpect(status().is3xxRedirection());
    }

    @Test
    void testLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login")
                .param("username", "user")
                .param("password", "test")

        ).andExpect(status().is2xxSuccessful());
    }

    @Test
    void testLoginFailure() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/login-error")
                .param("username", "user")
                .param("password", "test")
                .param("bad_credentials", "true")
                .with(csrf())
        ).andExpect(status().is2xxSuccessful());
    }
}
