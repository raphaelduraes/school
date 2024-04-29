package com.practice.school.integration;

import com.google.gson.Gson;
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
public class SubjectControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertNotNull(mockMvc);
    }

    @Test
    public void testGetSubjects() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/subjects/all");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }
    @Test
    public void testGetSubjectById() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/subjects/1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    public void testGetSubjectById_notFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/subjects/00");
        mockMvc.perform(request)
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testGetSubjectsByCourseId() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/subjects?course_id=1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    public void testCreateSubjects() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/subjects")
                .contentType(APPLICATION_JSON)
                .content(getStubJson());
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdateSubjects() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.put("/subjects/2")
                .contentType(APPLICATION_JSON)
                .content(getStubJson());
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdateSubjects_notFound() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.put("/subjects/000")
                .contentType(APPLICATION_JSON)
                .content(getStubJson());
        mockMvc.perform(request)
                .andExpect(status().isNotFound());
    }

    public void testDeleteSubjects() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.delete("/subjects/1");
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful());
    }

    private String getStubJson() {
        Subject stub = new Subject("Science", "John Doe");
        Gson gson = new Gson();
        return gson.toJson(stub);
    }
}
