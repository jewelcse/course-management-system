package com.bjit.course.controller;

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
class CourseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("create a new course test")
    void createNewCourse() throws Exception {

        String body = "{" +
                "    \n" +
                "    \"courseName\":\"English\",\n" +
                "    \"courseDescription\":\"Math courseDescription\",\n" +
                "    \"authorName\":\"Eadl\",\n" +
                "    \"genre\":\"Computer Science\",\n" +
                "    \"teacherId\":2\n" +
                "}";

        mockMvc.perform(post("/api/v1/courses")
                .content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("get course by course id test")
    void getCourseById() throws Exception {
        mockMvc.perform(get("/api/v1/courses/{id}", 2))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("get course details by course id test")
    void getCourseDetails() throws Exception {
        mockMvc.perform(get("/api/v1/courses/details/{id}", 3))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("get all courses test")
    void getAllCourse() throws Exception {
        mockMvc.perform(get("/api/v1/courses"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("get all courses by teacher test")
    void getAllCourseByTeacherId() throws Exception {
        mockMvc.perform(get("/api/v1/courses/teachers/{id}",1))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("update course test")
    void updateCourse() throws Exception {

        String body = "{\n" +
                "    \"courseName\":\"Algorithm-01\",\n" +
                "    \"courseDescription\":\"Introduction to Algorithms\",\n" +
                "    \"authorName\":\"Thomas H. Cormen\",\n" +
                "    \"genre\":\"Computer Science\",\n" +
                "    \"teacherId\":100,\n" +
                "    \"courseId\":2\n" +
                "}";

        mockMvc.perform(put("/api/v1/courses")
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("remove course by course id test")
    void removeCourseById() throws Exception {
        mockMvc.perform(delete("/api/v1/courses/{id}",2))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("enroll course by student test")
    void enrollCourse() throws Exception {

        String body = "{\n" +
                "    \"courseId\":3,\n" +
                "    \"studentId\":10\n" +
                "}";

        mockMvc.perform(post("/api/v1/courses/enroll",1)
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("get enrolled courses by student test")
    void getEnrolledCoursesByStudent() throws Exception {
        mockMvc.perform(get("/api/v1/courses/students/{id}",2))
                .andExpect(status().isOk());
    }
}