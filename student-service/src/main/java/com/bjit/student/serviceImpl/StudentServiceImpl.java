package com.bjit.student.serviceImpl;

import com.bjit.student.dto.request.CourseEnrollDto;
import com.bjit.student.dto.request.StudentCreateDto;
import com.bjit.student.dto.request.StudentLoginDto;
import com.bjit.student.dto.response.CourseResponseDto;
import com.bjit.student.dto.response.StudentFullProfileResponseDto;
import com.bjit.student.dto.response.StudentResponseDto;
import com.bjit.student.entity.Student;
import com.bjit.student.exception.IncorrectPasswordException;
import com.bjit.student.exception.StudentNotFoundException;
import com.bjit.student.mapper.StudentMapper;
import com.bjit.student.repository.StudentRepository;
import com.bjit.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.bjit.student.util.APIConstraints.COURSE_ENROLLMENT_API;
import static com.bjit.student.util.APIConstraints.GET_ENROLLED_COURSES_API;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
    private static final Log log = LogFactory.getLog(StudentServiceImpl.class);
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;

    @Override
    public boolean login(StudentLoginDto loginDto) {
        log.info("Logging student by email: " + loginDto.getEmail());
        StudentResponseDto response = getStudentByEmail(loginDto.getEmail());
        if (!matches(loginDto.getPassword(), response.getPassword())) {
            log.warn("Incorrect Password!");
            throw new IncorrectPasswordException("Incorrect Password!");
        }
        return true;
    }

    @Override
    public void register(StudentCreateDto registerDto) {
        log.info("Registering a new student for email: " + registerDto.getEmail());
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        studentRepository.save(studentMapper.toStudent(registerDto));
    }

    @Override
    public StudentResponseDto getStudentByEmail(String email) {
        log.info("Fetching student account for email: " + email);
        Student student = null;
        if (doesExistStudentAccount(email)) {
            student = studentRepository.findByEmail(email);
        } else {
            log.warn("Student account not found for email: " + email);
            throw new StudentNotFoundException("Student account not found for email: " + email);
        }
        return studentMapper.toStudentResponseDto(student);
    }

    @Override
    public void enrollToCourse(CourseEnrollDto courseEnrollDto) {
        log.info("Enrolling course by student for student id: " + courseEnrollDto.getStudentId() + " & course id: " + courseEnrollDto.getCourseId());
        ResponseEntity<String> res = restTemplate.postForEntity(COURSE_ENROLLMENT_API, courseEnrollDto, String.class);
        log.info(res.getBody());
    }

    @Override
    public StudentFullProfileResponseDto getStudentDetailsById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()){
            throw new StudentNotFoundException("Student not found for id: " + id);
        }
        ResponseEntity<CourseResponseDto[]> courses = restTemplate.getForEntity(GET_ENROLLED_COURSES_API+id,CourseResponseDto[].class);
        CourseResponseDto[] items = courses.getBody();

        StudentFullProfileResponseDto response = new StudentFullProfileResponseDto();
        response.setFirstName(student.get().getFirstName());
        response.setLastName(student.get().getLastName());
        response.setAddress(student.get().getAddress());
        response.setEmail(student.get().getEmail());
        response.setCourses(Arrays.asList(items));

        return response;
    }

    @Override
    public List<StudentResponseDto> getStudents() {
        return studentMapper.toStudentResponseDtos(studentRepository.findAll());
    }

    @Override
    public void deleteById(Long id) {
        if (!doesExistStudentAccount(id)){
            throw new StudentNotFoundException("student not found for id: "+ id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentResponseDto getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()){
            throw new StudentNotFoundException("student not found for id: " +id);
        }
        return studentMapper.toStudentResponseDto(student.get());
    }


    private boolean doesExistStudentAccount(String email) {
        return studentRepository.existsByEmail(email);
    }

    private boolean doesExistStudentAccount(Long id){
        return studentRepository.existsById(id);
    }

    private boolean matches(CharSequence rawPassword, String encodedPassword) {
        return !StringUtils.hasText(encodedPassword) || passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
