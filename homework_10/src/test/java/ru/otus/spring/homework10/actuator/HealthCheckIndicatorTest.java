package ru.otus.spring.homework10.actuator;

import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Description("Test class HealthCheckIndicator")
public class HealthCheckIndicatorTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @Description("Exists all values HealthCheckIndicator")
    public void existsAllValuesMyIndicator() throws Exception {
        mvc.perform(get("/actuator/health/myHealth"))
                .andExpect(jsonPath("$.status").exists())
                .andExpect(jsonPath("$.details.message").exists());
    }

    @Test
    @Description("Status OK HealthCheckIndicator")
    public void givenMyIndicator() throws Exception {
        mvc.perform(get("/actuator/health/myHealth"))
                .andExpect(status().isOk());
    }
}
