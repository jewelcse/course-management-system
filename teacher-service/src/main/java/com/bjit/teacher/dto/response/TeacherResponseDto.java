package com.bjit.teacher.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeacherResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String designation;
    private String address;
    private String password;
    private List<CourseResponseDto> courses = new ArrayList<>();
}
