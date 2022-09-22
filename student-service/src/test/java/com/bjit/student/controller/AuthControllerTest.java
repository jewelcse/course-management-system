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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("sign in student account test")
    void signIn() throws Exception {

        String body = "{\n" +
                " \n" +
                "    \"email\":\"emam4@gmail.com\",\n" +
                "    \"password\":\"emam\"\n" +
                "\n" +
                "}";

        mockMvc.perform(post("/api/v1/students/signin")
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());

    }

    @Test
    @DisplayName("register student account test")
    void signUp() throws Exception {

        String body = "{\n" +
                "    \"firstName\":\"Md4\",\n" +
                "    \"lastName\":\"Emam4\",\n" +
                "    \"email\":\"emam5@gmail.com\",\n" +
                "    \"phone\":\"329837\",\n" +
                "    \"password\":\"emam\",\n" +
                "    \"address\":\"Dhaka\"\n" +
                "}";

        mockMvc.perform(post("/api/v1/students/signup")
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());

    }
}