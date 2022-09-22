package com.bjit.teacher.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeacherUpdateDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String designation;
    private String address;
}
