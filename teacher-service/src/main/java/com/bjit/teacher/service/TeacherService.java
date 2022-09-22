package com.bjit.teacher.service;

import com.bjit.teacher.dto.request.*;
import com.bjit.teacher.dto.response.TeacherResponseDto;
import com.bjit.teacher.entity.Teacher;

import java.util.List;

public interface TeacherService {

    void saveTeacher(TeacherCreateDto teacherCreateDto);
    boolean loginTeacher(TeacherLoginDto teacherLoginDto);
    TeacherResponseDto getTeacherByEmail(String email);
    TeacherResponseDto getTeacher(Long teacherId);
    List<TeacherResponseDto> getTeachers();
    TeacherResponseDto updateTeacher(TeacherUpdateDto teacherUpdateDto);
    void removeTeacher(Long teacherId);

}
