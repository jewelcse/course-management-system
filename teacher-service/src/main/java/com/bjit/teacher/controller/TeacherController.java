package com.bjit.teacher.controller;


import com.bjit.teacher.dto.response.TeacherResponseDto;
import com.bjit.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bjit.teacher.util.APIConstraints.APPLICATION_BASE_API;

@RequiredArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_API)
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherResponseDto>> getTeachers() {
        return ResponseEntity.ok(teacherService.getTeachers());
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherResponseDto> getTeacher(@PathVariable("id") Long id) {
        return ResponseEntity.ok(teacherService.getTeacher(id));
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable("id") Long id) {
        teacherService.removeTeacher(id);
        return ResponseEntity.ok("Account deleted Success");
    }

    // todo: update teacher account
    // todo: reset user password
}
