package org.example.springprj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUsersReturnsOkAndJsonArray() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void postUserCreatesNewUser() throws Exception {
        String json = """
                {
                  "id": 4,
                  "name": "David"
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("David"));

        mockMvc.perform(get("/users"))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(content().string(containsString("David")));
    }

    @Test
    void getUsersWithInvalidMinId() throws Exception {
        mockMvc.perform(get("/users?minId=0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUsersWithValidMinId() throws Exception {
        mockMvc.perform(get("/users?minId=1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
    }


}
