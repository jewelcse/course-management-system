package com.bjit.student.serviceImpl;

import com.bjit.student.dto.request.StudentCreateDto;
import com.bjit.student.dto.response.StudentResponseDto;
import com.bjit.student.entity.Student;
import com.bjit.student.mapper.StudentMapper;
import com.bjit.student.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class StudentServiceUnitTest {

    @InjectMocks
    private StudentServiceImpl studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private StudentMapper studentMapper;

    private StudentCreateDto studentCreateDto  = null;
    private StudentResponseDto studentResponseDto = null;
    private Student student = null;
    List<Student> students = new ArrayList<>();
    List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
    String encodedPassword = "";



    @BeforeEach
    void setUp(){
        encodedPassword = "$2a$10$9W8lz2XKUAC5QRXFsTaq7uFivwhaPVlB7j55NIQjkXZ2CKTr3iXYm";
        studentCreateDto = new StudentCreateDto("jewel","chowdhury","jewel@gmail.com","jewel123","Dhaka");
        studentResponseDto = new StudentResponseDto("jewel","chowdhury","jewel@gmail.com","jewel123","Dhaka");
        student = new Student(1L,"jewel","chowdhury","jewel@gmail.com","jewel123","Dhaka");
        students = Arrays.asList(student,student);
        studentResponseDtos = Arrays.asList(studentResponseDto,studentResponseDto);
    }

    @Test
    @DisplayName("student new account create test()")
    void registerStudentTest(){
        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);
        when(studentMapper.toStudent(studentCreateDto)).thenReturn(student);
        studentService.register(studentCreateDto);

        verify(studentRepository).save(student);
    }



}