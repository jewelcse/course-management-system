package com.bjit.teacher.service;

import com.bjit.teacher.dto.request.CourseCreateDto;
import com.bjit.teacher.dto.request.CourseUpdateDto;
import com.bjit.teacher.dto.response.CourseResponseDto;

import java.util.List;

public interface CourseService {
    void saveCourse(CourseCreateDto course);
    CourseResponseDto getCourseById(Long courseId);
    List<CourseResponseDto> getAllCourseBYTeacherIds(Long id);
    CourseResponseDto updateCourse(CourseUpdateDto course);
    void removeCourseById(Long courseId);

}
