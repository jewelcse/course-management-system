package com.bjit.course.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CourseCreateDto {
    @NotEmpty(message = "Course name can't be null")
    private String courseName;
    @NotEmpty(message = "Course description can't be null")
    private String courseDescription;
    @NotEmpty(message = "Author name can't be empty")
    private String authorName;
    @NotEmpty(message = "Genre can't be null")
    private String genre;
    @NotNull(message = "Teacher id can't be empty")
    private Long teacherId;
}
