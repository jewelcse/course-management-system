package com.bjit.teacher.serviceImpl;

import com.bjit.teacher.dto.request.TeacherCreateDto;
import com.bjit.teacher.dto.request.TeacherLoginDto;
import com.bjit.teacher.dto.request.TeacherUpdateDto;
import com.bjit.teacher.dto.response.TeacherResponseDto;
import com.bjit.teacher.entity.Teacher;
import com.bjit.teacher.exception.IncorrectPasswordException;
import com.bjit.teacher.exception.TeacherNotFoundException;
import com.bjit.teacher.mapper.TeacherMapper;
import com.bjit.teacher.repository.TeacherRepository;
import com.bjit.teacher.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {
    private static final Log log = LogFactory.getLog(TeacherServiceImpl.class);
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveTeacher(TeacherCreateDto teacherCreateDto) {
        log.info("Registering teacher account for email: " + teacherCreateDto.getEmail());
        teacherCreateDto.setPassword(passwordEncoder.encode(teacherCreateDto.getPassword()));
        teacherRepository.save(teacherMapper.toTeacher(teacherCreateDto));
    }

    @Override
    public boolean loginTeacher(TeacherLoginDto teacherLoginDto) {
        log.info("Logging teacher account with email: " + teacherLoginDto.getEmail());
        TeacherResponseDto response = getTeacherByEmail(teacherLoginDto.getEmail());
        if (!matchPassword(teacherLoginDto.getPassword(), response.getPassword())) {
            log.warn("Incorrect Password!");
            throw new IncorrectPasswordException("Incorrect Password!");
        }
        return true;
    }

    @Override
    public TeacherResponseDto getTeacherByEmail(String email) {
        log.info("Fetching teacher account for email: " + email);
        Teacher teacher = null;
        if (doesFoundTeacherAccountByEmail(email)) {
            teacher = teacherRepository.findByEmail(email);
        } else {
            log.warn("Teacher Account Not found for email: " + email);
            throw new TeacherNotFoundException("Teacher Account Not found for email: " + email);
        }
        return teacherMapper.toTeacherResponseDto(teacher);
    }

    @Override
    public TeacherResponseDto getTeacher(Long id) {
        log.info("Fetching teacher account for id: " + id);
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()) {
            log.warn("Teacher not found for id: " + id);
            throw new TeacherNotFoundException("Teacher not found for id: " + id);
        }
        return teacherMapper.toTeacherResponseDto(teacher.get());
    }

    @Override
    public List<TeacherResponseDto> getTeachers() {
        log.info("Fetching all teachers");
        return teacherMapper
                .toTeacherResponseDtos(teacherRepository.findAll());
    }

    @Override
    public TeacherResponseDto updateTeacher(TeacherUpdateDto teacherUpdateDto) {
        log.info("Updating teacher account for email: " + teacherUpdateDto.getEmail());
        //todo: update teacher account
        return null;
    }

    @Override
    public void removeTeacher(Long id) {
        log.info("Removing teacher account for id: " + id);
        if (!doesExistTeacherAccountById(id)) {
            log.warn("Teacher not found for id: " + id);
            throw new TeacherNotFoundException("Teacher not found for id:  " + id);
        }
        teacherRepository.deleteById(id);
    }

    public boolean matchPassword(CharSequence rawPassword, String encodedPassword){
        return matches(rawPassword, encodedPassword);
    }

    public boolean doesFoundTeacherAccountByEmail(String email){
        return doesExistTeacherAccount(email);
    }
    private boolean matches(CharSequence rawPassword, String encodedPassword) {
        return !StringUtils.hasText(encodedPassword) || passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private boolean doesExistTeacherAccount(String email) {
        return teacherRepository.existsByEmail(email);
    }

    private boolean doesExistTeacherAccountById(Long id) {
        return teacherRepository.existsById(id);
    }
}
