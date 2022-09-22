package com.bjit.course.mapper;

import com.bjit.course.dto.request.CourseCreateDto;
import com.bjit.course.dto.request.CourseEnrollDto;
import com.bjit.course.dto.request.CourseUpdateDto;
import com.bjit.course.dto.response.CourseResponseDto;
import com.bjit.course.entity.Course;
import com.bjit.course.entity.CourseEnrollment;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CourseMapper {
    Course toCourse(CourseCreateDto course);

    Course toCourse(CourseUpdateDto course);

    CourseEnrollment toCourseEnrollment(CourseEnrollDto courseEnrollDto);

    CourseResponseDto toCourseResponseDto(Course course);

    List<CourseResponseDto> toCourseResponseDto(List<Course> courses);
}
