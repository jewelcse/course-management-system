package com.bjit.teacher.controller;

import com.bjit.teacher.dto.request.TeacherCreateDto;
import com.bjit.teacher.dto.request.TeacherLoginDto;
import com.bjit.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.bjit.teacher.util.APIConstraints.APPLICATION_BASE_API;

@RequiredArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_API)
public class TeacherAuthController {
    private final TeacherService teacherService;

    @PostMapping("/teachers/signup")
    public ResponseEntity<String> signUp(@RequestBody TeacherCreateDto teacherCreateDto) {
        teacherService.saveTeacher(teacherCreateDto);
        return new ResponseEntity<>("Registration Completed Successfully!", HttpStatus.CREATED);
    }

    @PostMapping("/teachers/signin")
    public ResponseEntity<Object> signIn(@RequestBody TeacherLoginDto teacherLoginDto) {
        if (teacherService.loginTeacher(teacherLoginDto)) {
            return new ResponseEntity<>("Login Successful!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Incorrect Password!", HttpStatus.BAD_REQUEST);
    }
}
