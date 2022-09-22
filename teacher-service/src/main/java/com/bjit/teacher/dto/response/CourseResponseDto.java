package com.bjit.teacher.dto.response;

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
    private double courseReview;
}
