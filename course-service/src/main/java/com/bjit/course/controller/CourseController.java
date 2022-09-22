package com.bjit.course.controller;


import com.bjit.course.dto.request.CourseCreateDto;
import com.bjit.course.dto.request.CourseEnrollDto;
import com.bjit.course.dto.request.CourseUpdateDto;
import com.bjit.course.dto.response.CourseDetailsDto;
import com.bjit.course.dto.response.CourseResponseDto;
import com.bjit.course.exception.CourseNotFoundException;
import com.bjit.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.bjit.course.util.APIConstraints.APPLICATION_BASE_API;

@RequiredArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_API)
public class CourseController {
    private final CourseService courseService;

    @PostMapping("/courses")
    public ResponseEntity<String> createNewCourse(@Valid @RequestBody CourseCreateDto courseCreateDto) {
        courseService.saveCourse(courseCreateDto);
        return new ResponseEntity<>("Course Created Success!", HttpStatus.CREATED);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponseDto> getCourseById(@PathVariable("id") Long id){
        return new ResponseEntity<>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @GetMapping("/courses/details/{id}")
    public ResponseEntity<CourseDetailsDto> getCourseDetails(@PathVariable("id") Long id) {
        return new ResponseEntity<>(courseService.getFullCourseDetails(id), HttpStatus.OK);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseResponseDto>> getAllCourse() {
        return new ResponseEntity<>(courseService.getAllCourse(), HttpStatus.OK);
    }

    @GetMapping("/courses/teachers/{id}")
    public ResponseEntity<List<CourseResponseDto>> getAllCourseByTeacherId(@PathVariable("id") Long id) {
        return new ResponseEntity<>(courseService.getAllCourseByTeacherId(id), HttpStatus.OK);
    }

    @PutMapping("/courses")
    public ResponseEntity<CourseResponseDto> updateCourse(@Valid @RequestBody CourseUpdateDto courseUpdateDto) {
        return new ResponseEntity<>(courseService.updateCourse(courseUpdateDto), HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<String> removeCourseById(@PathVariable("id") Long id) {
        courseService.removeCourseById(id);
        return new ResponseEntity<>("Course Deleted Success!", HttpStatus.OK);
    }

    @PostMapping("/courses/enroll")
    public ResponseEntity<String> enrollCourse(@Valid @RequestBody CourseEnrollDto courseEnrollDto) {
        courseService.enrollNewCourse(courseEnrollDto);
        return new ResponseEntity<>("Course Enrolled Success", HttpStatus.CREATED);
    }

    @GetMapping("/courses/students/{id}")
    public ResponseEntity<List<CourseResponseDto>> getEnrolledCoursesByStudent(@PathVariable("id") Long id){
        return ResponseEntity.ok(courseService.getAllCourseByStudentId(id));
    }
}
