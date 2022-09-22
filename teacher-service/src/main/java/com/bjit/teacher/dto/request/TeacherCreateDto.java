package com.bjit.teacher.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TeacherCreateDto {
    @NotEmpty(message = "First name can't be null")
    private String firstName;
    @NotEmpty(message = "Last name can't be null")
    private String lastName;
    @NotEmpty(message = "Email can't be null")
    private String email;
    @NotEmpty(message = "Phone name can't be null")
    private String phone;
    @NotEmpty(message = "Password can't be null")
    private String password;
    @NotEmpty(message = "Designation can't be null")
    private String designation;
    @NotEmpty(message = "Address can't be null")
    private String address;
}
