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
public class GameControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void addTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/games/add")
                .param("title", "Light souls")
                        .param("description", "Lorem ipsum")
                        .param("price", "10")
                        .param("category", "RPG")
                        .with(csrf())

                ).andExpect(status().is3xxRedirection());
    }

}
