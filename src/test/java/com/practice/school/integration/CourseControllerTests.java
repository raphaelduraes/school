package com.practice.school.integration;

import com.google.gson.Gson;
import com.practice.school.model.Course;
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
    public void testGetCourses() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/courses/all");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }
    @Test
    public void testGetCourseById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/courses/1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    public void testGetCourseById_notFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/courses/00");
        mockMvc.perform(request)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetCourseByStudentId() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/courses?student=1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    public void testCreateCourse() throws Exception {
        Course stub = new Course("Test 1", Course.Shift.AFTERNOON);
        Gson gson = new Gson();
        String json = gson.toJson(stub);
        RequestBuilder request = MockMvcRequestBuilders.post("/courses")
                .contentType(APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdateCourse() throws Exception {
        Course stub = new Course("Test Updated", Course.Shift.EVENING);
        Gson gson = new Gson();
        String json = gson.toJson(stub);
        RequestBuilder request = MockMvcRequestBuilders.put("/courses/2")
                .contentType(APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdateCourse_notFound() throws Exception {
        Course stub = new Course("Test Updated", Course.Shift.EVENING);
        Gson gson = new Gson();
        String json = gson.toJson(stub);
        RequestBuilder request = MockMvcRequestBuilders.put("/courses/000")
                .contentType(APPLICATION_JSON)
                .content(json);
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    public void testDeleteCourse() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/courses/1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }
}
