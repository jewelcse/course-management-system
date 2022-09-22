package com.bjit.student.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CourseResponseDto {
    private String courseName;
    private String courseDescription;
    private String authorName;
    private String genre;
    private Long teacherId;
    private double courseReview;
}
