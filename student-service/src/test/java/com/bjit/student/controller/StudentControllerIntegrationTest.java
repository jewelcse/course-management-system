package com.bjit.student.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("enroll to a new course by student test")
    void enroll() throws Exception {
        String body = "{\n" +
                "    \"courseId\":6,\n" +
                "    \"studentId\":3\n" +
                "}";

        mockMvc.perform(post("/api/v1/students/enroll")
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("get student by student id test")
    void getStudentById() throws Exception {

        mockMvc.perform(get("/api/v1/students/{id}", 4))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("get student details by student id test")
    void getStudentDetailsById() throws Exception {
        mockMvc.perform(get("/api/v1/students/details/{id}", 4))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("get all students test")
    void getAll() throws Exception {
        mockMvc.perform(get("/api/v1/students"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("remove student by student id test")
    void remove() throws Exception {
        mockMvc.perform(delete("/api/v1/students/{id}",3))
                .andExpect(status().isOk());
    }
}