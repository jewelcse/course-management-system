package com.bjit.teacher.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TeacherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("get all teachers test")
    void getTeachers() throws Exception {
        mockMvc.perform(get("/api/v1/teachers"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("get teacher account by id test")
    void getTeacherById() throws Exception {
        mockMvc.perform(get("/api/v1/teachers/{id}",4))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("remove teacher account by id test")
    void deleteTeacher() throws Exception {
        mockMvc.perform(delete("/api/v1/teachers/{id}",2))
                .andExpect(status().isOk());
    }
}