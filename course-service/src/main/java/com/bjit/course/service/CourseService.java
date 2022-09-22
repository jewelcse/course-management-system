package com.bjit.course.service;

import com.bjit.course.dto.request.CourseCreateDto;
import com.bjit.course.dto.request.CourseEnrollDto;
import com.bjit.course.dto.request.CourseUpdateDto;
import com.bjit.course.dto.response.CourseDetailsDto;
import com.bjit.course.dto.response.CourseResponseDto;
import com.bjit.course.exception.CourseNotFoundException;

import java.util.List;

public interface CourseService {
    void saveCourse(CourseCreateDto course);
    CourseResponseDto getCourseById(Long courseId);
    List<CourseResponseDto> getAllCourse();
    List<CourseResponseDto> getAllCourseByTeacherId(Long teacherId);
    CourseResponseDto updateCourse(CourseUpdateDto course);
    void removeCourseById(Long courseId);
    void enrollNewCourse(CourseEnrollDto courseEnrollDto);
    CourseDetailsDto getFullCourseDetails(Long id);

    List<CourseResponseDto> getAllCourseByStudentId(Long id);
}
