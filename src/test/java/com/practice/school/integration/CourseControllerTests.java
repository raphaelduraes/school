package com.practice.school.integration;

import jdk.jfr.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CourseControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertNotNull(mockMvc);
    }
    @Test
    public void testGetCourseById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/course/1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }
}
