package com.bjit.course.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseEnrollDto {
    @NotNull(message = "Course Id can't be Empty")
    private Long courseId;
    @NotNull(message = "Student Id can't be Empty")
    private Long studentId;
}
