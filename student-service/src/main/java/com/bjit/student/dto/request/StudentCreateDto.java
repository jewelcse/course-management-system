package com.bjit.student.dto.request;


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
public class StudentCreateDto {
    @NotEmpty(message = "First name can't be null")
    private String firstName;
    @NotEmpty(message = "Last name can't be null")
    private String lastName;
    @NotEmpty(message = "Email name can't be null")
    private String email;
    @NotEmpty(message = "Password name can't be null")
    private String password;
    @NotEmpty(message = "Address name can't be null")
    private String address;
}
