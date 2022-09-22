package com.bjit.course.dto.response;

import com.bjit.course.dto.StudentDto;
import com.bjit.course.dto.TeacherDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseDetailsDto {
    private String courseName;
    private String courseDescription;
    private String authorName;
    private String genre;
    private double courseReview;
    private TeacherDto teacher;
    private List<StudentDto> students;
}
