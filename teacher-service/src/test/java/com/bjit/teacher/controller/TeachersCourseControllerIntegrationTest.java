package com.bjit.teacher.controller;

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
class TeachersCourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("create course by teacher test")
    void createCourse() throws Exception {
        String body = "{\n" +
                "    \"courseName\":\"Algorithm-03\",\n" +
                "    \"courseDescription\":\"Introduction to Algorithms\",\n" +
                "    \"authorName\":\"Thomas H. Cormen\",\n" +
                "    \"genre\":\"Computer Science\",\n" +
                "    \"teacherId\":2\n" +
                "}";

        mockMvc.perform(post("/api/v1/teachers/create-courses")
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());

    }

    @Test
    @DisplayName("get all courses by teacher test")
    void getCoursesByTeacher() throws Exception {
        mockMvc.perform(get("/api/v1/teachers/{id}/courses",1))
                .andExpect(status().isOk());

    }

    @Test
    @DisplayName("get course by course id test")
    void getCourseByCourseId() throws Exception {
        mockMvc.perform(get("/api/v1/teachers/courses/{id}",4))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("remove course by teacher test")
    void removeCourseByTeacher() throws Exception {
        mockMvc.perform(delete("/api/v1/teachers/courses/{id}",4))
                .andExpect(status().isOk());
    }
}