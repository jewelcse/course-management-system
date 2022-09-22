package com.bjit.course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TeacherDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String designation;
    private String address;
    private String password;
}
