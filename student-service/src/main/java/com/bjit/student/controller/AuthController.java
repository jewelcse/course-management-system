package com.bjit.student.controller;

import com.bjit.student.dto.request.StudentCreateDto;
import com.bjit.student.dto.request.StudentLoginDto;
import com.bjit.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.bjit.student.util.APIConstraints.APPLICATION_BASE_API;

@RequiredArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_API)
public class AuthController {
    private final StudentService studentService;
    @PostMapping("/students/signin")
    public ResponseEntity<String> signIn(@Valid @RequestBody StudentLoginDto studentLoginDto) {
        if (studentService.login(studentLoginDto)){
            return new ResponseEntity<>("Login Successful!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Login Failed", HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/students/signup")
    public ResponseEntity<String> signUp(@Valid @RequestBody StudentCreateDto studentCreateDto) {
        studentService.register(studentCreateDto);
        return new ResponseEntity<>("Registration Completed Successfully!", HttpStatus.CREATED);
    }
}
