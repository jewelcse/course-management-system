package com.bjit.course.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseResponseDto {
    private String courseName;
    private String courseDescription;
    private String authorName;
    private String genre;
    private Long teacherId;
    private double courseReview;
}
