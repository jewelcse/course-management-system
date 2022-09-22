package com.bjit.student.controller;

import com.bjit.student.dto.request.CourseEnrollDto;
import com.bjit.student.dto.response.StudentFullProfileResponseDto;
import com.bjit.student.dto.response.StudentResponseDto;
import com.bjit.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.bjit.student.util.APIConstraints.APPLICATION_BASE_API;

@RequiredArgsConstructor
@RestController
@RequestMapping(APPLICATION_BASE_API)
public class StudentController {
    private final StudentService studentService;
    @PostMapping("/students/enroll")
    public ResponseEntity<String> enroll(@Valid @RequestBody CourseEnrollDto courseEnrollDto){
        studentService.enrollToCourse(courseEnrollDto);
        return ResponseEntity.ok("Enrolled success");
    }
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable("id") Long id){
        return ResponseEntity.ok(studentService.getStudentById(id));
    }
    @GetMapping("/students/details/{id}")
    public ResponseEntity<StudentFullProfileResponseDto> getStudentDetailsById(@PathVariable("id") Long id){
        return ResponseEntity.ok(studentService.getStudentDetailsById(id));
    }
    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDto>> getAll(){
        return ResponseEntity.ok(studentService.getStudents());
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> remove(@PathVariable("id") Long id){
        studentService.deleteById(id);
        return ResponseEntity.ok("Deleted success");
    }

}
