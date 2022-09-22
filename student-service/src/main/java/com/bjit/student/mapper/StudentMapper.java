package com.bjit.student.mapper;

import com.bjit.student.dto.request.StudentCreateDto;
import com.bjit.student.dto.response.StudentFullProfileResponseDto;
import com.bjit.student.dto.response.StudentResponseDto;
import com.bjit.student.entity.Student;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toStudent(StudentCreateDto studentCreateDto);
    StudentResponseDto toStudentResponseDto(Student student);
    List<StudentResponseDto> toStudentResponseDtos(List<Student> students);
}
