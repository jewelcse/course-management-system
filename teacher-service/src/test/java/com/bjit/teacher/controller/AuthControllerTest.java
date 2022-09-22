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
    @DisplayName("teacher register new account test")
    void signUp() throws Exception {
        String body = "{\n" +
                "    \"firstName\":\"Md. \",\n" +
                "    \"lastName\":\"Abdulliah\",\n" +
                "    \"email\":\"Abdulliah@gmail.com1\",\n" +
                "    \"phone\":\"329837\",\n" +
                "    \"password\":\"sams123\",\n" +
                "    \"designation\":\"Masters\",\n" +
                "    \"address\":\"Barishal\"\n" +
                "}";

        mockMvc.perform(post("/api/v1/teachers/signup")
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("teacher login account test")
    void signIn() throws Exception {
        String body = "{\n" +
                " \n" +
                "    \"email\":\"sams123@gmail.com1\",\n" +
                "    \"password\":\"sams123\"\n" +
                "\n" +
                "}";
        mockMvc.perform(post("/api/v1/teachers/signin")
                        .content(body).contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().is2xxSuccessful());

    }
}