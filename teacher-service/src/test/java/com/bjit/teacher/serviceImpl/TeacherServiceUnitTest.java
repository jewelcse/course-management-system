package com.bjit.teacher.serviceImpl;

import com.bjit.teacher.dto.request.TeacherCreateDto;
import com.bjit.teacher.dto.request.TeacherLoginDto;
import com.bjit.teacher.dto.response.TeacherResponseDto;
import com.bjit.teacher.entity.Teacher;
import com.bjit.teacher.mapper.TeacherMapper;
import com.bjit.teacher.repository.TeacherRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TeacherServiceUnitTest {

    @InjectMocks
    private TeacherServiceImpl teacherService;
    @Mock
    private TeacherMapper teacherMapper;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private TeacherRepository teacherRepository;

    private TeacherCreateDto teacherCreateDto = null;
    private TeacherResponseDto teacherResponseDto = null;
    private TeacherLoginDto teacherLoginDto = null;
    private Teacher teacher = null;
    private List<Teacher> teachers = new ArrayList<>();
    private List<TeacherResponseDto> teacherResponseDtos = new ArrayList<>();
    String encodedPassword = "";


    @BeforeEach
    void setUp(){
        encodedPassword = "$2a$10$9W8lz2XKUAC5QRXFsTaq7uFivwhaPVlB7j55NIQjkXZ2CKTr3iXYm";
        teacher = new Teacher(1L,"Md Sams","Alam","mdsams@gmail.com","3243432","123","AP","Dhaka");
        teacherCreateDto = new TeacherCreateDto("Md Sams","Alam","mdsams@gmail.com","3243432","123","AP","Dhaka");
        teacherResponseDto = new TeacherResponseDto("Md Sams","Alam","mdsams@gmail.com","3243432","123","AP","Dhaka", new ArrayList<>());
        teacherLoginDto = new TeacherLoginDto("mdsams@gmail.com","12345");
        teachers = Arrays.asList(teacher, teacher);
        teacherResponseDtos = Arrays.asList(teacherResponseDto,teacherResponseDto);
    }

    @Test
    @DisplayName("creating teacher new account test()")
    void creatingTeacherAccountTest(){
        when(passwordEncoder.encode(teacherCreateDto.getPassword())).thenReturn(encodedPassword);
        when(teacherMapper.toTeacher(teacherCreateDto)).thenReturn(teacher);

        teacherService.saveTeacher(teacherCreateDto);

        verify(teacherRepository).save(teacher);
    }

    @Test
    @DisplayName("login account by teacher test()")
    void loginTeacherAccountTest(){
//        String email = "mdsams@gmail.com";
//        when(teacherService.getTeacherByEmail(email)).thenReturn(teacherResponseDto);
//        when(teacherService.doesFoundTeacherAccountByEmail(email)).thenReturn(true);
//        when(teacherRepository.findByEmail(email)).thenReturn(teacher);
//        when(teacherMapper.toTeacherResponseDto(teacher)).thenReturn(teacherResponseDto);
//
//        assertTrue(teacherService.loginTeacher(teacherLoginDto));
    }

    @Test
    @DisplayName("get teacher by id test()")
    void getTeacherByIdTest(){
        Long id = 1L;
        when(teacherRepository.findById(id)).thenReturn(Optional.of(teacher));
        when(teacherMapper.toTeacherResponseDto(teacher)).thenReturn(teacherResponseDto);

        assertEquals("mdsams@gmail.com",teacherService.getTeacher(id).getEmail());
    }

    @Test
    @DisplayName("get teachers list test()")
    void getTeachersTest(){
        when(teacherRepository.findAll()).thenReturn(teachers);
        when(teacherMapper.toTeacherResponseDtos(teachers)).thenReturn(teacherResponseDtos);

        assertEquals(2,teacherService.getTeachers().size());

    }



}