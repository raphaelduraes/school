package com.practice.school.integration;

import com.google.gson.Gson;
import com.practice.school.model.Grade;
import com.practice.school.model.Student;
import com.practice.school.model.Subject;
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
public class GradeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertNotNull(mockMvc);
    }

    @Test
    public void testGetGradeById_notFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/grades/00");
        mockMvc.perform(request)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetGradesBySubjectId() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/grades?subject_id=1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    public void testGetGradeById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/grades/2");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    public void testGetGradesByStudentId() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/grades?student_id=1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    public void testCreateGrade() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/grades")
                .contentType(APPLICATION_JSON)
                .content(getStubJson());
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdateGrade() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.put("/grades/2")
                .contentType(APPLICATION_JSON)
                .content(getStubJson());
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdateGrade_notFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.put("/grades/000")
                .contentType(APPLICATION_JSON)
                .content(getStubJson());
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteGrade() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/grades/1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    private String getStubJson() {
        Subject subject = new Subject("Graphs", "Joe");
        subject.setId(1L);
        Student student = new Student("Edward", "7 Lamb Alley", "asfdgf@sdf.com", "+353873409");
        student.setId(1L);
        Grade stub = new Grade("A+", subject, student);
        Gson gson = new Gson();
        return gson.toJson(stub);
    }
}
