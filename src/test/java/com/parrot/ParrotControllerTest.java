package com.parrot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ParrotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void rootEndpointReturnsExpectedFields() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hostname").exists())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.uptime").exists())
                .andExpect(jsonPath("$.color").exists())
                .andExpect(jsonPath("$.env").exists());
    }

    @Test
    void colorDefaultsToUnknown() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.color").value("unknown"));
    }
}
