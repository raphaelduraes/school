package com.practice.school.integration;

import com.google.gson.Gson;
import com.practice.school.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertNotNull(mockMvc);
    }

    @Test
    public void testGetStudents() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/students/all");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }
    @Test
    public void testGetStudentById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/students/1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    public void testGetStudentById_notFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/students/00");
        mockMvc.perform(request)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetStudentsByCourseId() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/students?course_id=1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    public void testCreateStudents() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/students")
                .contentType(APPLICATION_JSON)
                .content(getStubJson());
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdateStudents() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.put("/students/2")
                .contentType(APPLICATION_JSON)
                .content(getStubJson());
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdateStudents_notFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.put("/students/000")
                .contentType(APPLICATION_JSON)
                .content(getStubJson());
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    public void testDeleteStudents() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/students/1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    private String getStubJson() {
        Student stub = new Student("Raphael", "65 O'Connell St", "aksjfasd@gmail.com", "0877015743");
        Gson gson = new Gson();
        return gson.toJson(stub);
    }
}
