package com.bjit.student.service;

import com.bjit.student.dto.request.CourseEnrollDto;
import com.bjit.student.dto.request.StudentCreateDto;
import com.bjit.student.dto.request.StudentLoginDto;
import com.bjit.student.dto.response.StudentFullProfileResponseDto;
import com.bjit.student.dto.response.StudentResponseDto;

import java.util.List;

public interface StudentService {
    boolean login(StudentLoginDto studentLoginDto);
    StudentResponseDto getStudentByEmail(String email);
    void register(StudentCreateDto studentCreateDto);
    void enrollToCourse(CourseEnrollDto courseEnrollDto);
    StudentFullProfileResponseDto getStudentDetailsById(Long id);
    List<StudentResponseDto> getStudents();

    void deleteById(Long id);
    StudentResponseDto getStudentById(Long id);
}
