package com.bjit.teacher.controller;

import com.bjit.teacher.dto.request.CourseCreateDto;
import com.bjit.teacher.dto.response.CourseResponseDto;
import com.bjit.teacher.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import static com.bjit.teacher.util.APIConstraints.APPLICATION_BASE_API;

@RequiredArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_API)
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/teachers/create-courses")
    public ResponseEntity<String> createCourse(@Valid @RequestBody CourseCreateDto courseCreateDto) {
        courseService.saveCourse(courseCreateDto);
        return ResponseEntity.ok("Course created Successfully");
    }

    @GetMapping("/teachers/{id}/courses")
    public ResponseEntity<Object> getCourses(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getAllCourseBYTeacherIds(id));
    }
    @GetMapping("/teachers/courses/{id}")
    public ResponseEntity<CourseResponseDto> getCourseBYId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
    @DeleteMapping("/teachers/courses/{id}")
    public ResponseEntity<String> removeCourseByTeacher(@PathVariable("id") Long id) {
        courseService.removeCourseById(id);
        return ResponseEntity.ok("Course Removed Success!");
    }
}
