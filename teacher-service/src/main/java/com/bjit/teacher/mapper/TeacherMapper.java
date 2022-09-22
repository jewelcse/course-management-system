package com.bjit.teacher.mapper;

import com.bjit.teacher.dto.request.TeacherCreateDto;
import com.bjit.teacher.dto.response.TeacherResponseDto;
import com.bjit.teacher.entity.Teacher;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    Teacher toTeacher(TeacherCreateDto teacherCreateDto);

    TeacherResponseDto toTeacherResponseDto(Teacher teacher);

    List<TeacherResponseDto> toTeacherResponseDtos(List<Teacher> teachers);

}
