package com.bjit.course.serviceImpl;

import com.bjit.course.controller.CourseController;
import com.bjit.course.dto.StudentDto;
import com.bjit.course.dto.TeacherDto;
import com.bjit.course.dto.request.CourseCreateDto;
import com.bjit.course.dto.request.CourseEnrollDto;
import com.bjit.course.dto.request.CourseUpdateDto;
import com.bjit.course.dto.response.CourseDetailsDto;
import com.bjit.course.dto.response.CourseResponseDto;
import com.bjit.course.entity.Course;
import com.bjit.course.entity.CourseEnrollment;
import com.bjit.course.exception.CourseAlreadyEnrolledException;
import com.bjit.course.exception.CourseNotFoundException;
import com.bjit.course.mapper.CourseMapper;
import com.bjit.course.repository.CourseEnrollmentRepository;
import com.bjit.course.repository.CourseRepository;
import com.bjit.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.bjit.course.util.APIConstraints.GET_STUDENT_BY_ID;
import static com.bjit.course.util.APIConstraints.GET_TEACHER_BY_ID;


@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private static final Log log = LogFactory.getLog(CourseController.class);
    private final CourseRepository courseRepository;
    private final CourseEnrollmentRepository courseEnrollmentRepository;
    private final CourseMapper courseMapper;
    private final RestTemplate restTemplate;

    @Override
    public void saveCourse(CourseCreateDto course) {
        log.info("Saving course : " + course.getCourseName());
        courseRepository.save(courseMapper.toCourse(course));
    }

    @Override
    public CourseResponseDto getCourseById(Long id) {
        log.info("Fetching course for id: " + id);
        courseAvailabilityCheck(id);
        Optional<Course> course = courseRepository.findById(id);
        return courseMapper.toCourseResponseDto(course.orElseGet(Course::new));
    }

    @Override
    public CourseDetailsDto getFullCourseDetails(Long id) {
        log.info("Fetching full course details for id : " + id);
        courseAvailabilityCheck(id);
        Course course = courseRepository.findById(id).get();
        TeacherDto teacher = restTemplate.getForObject(GET_TEACHER_BY_ID +
                course.getTeacherId(), TeacherDto.class);

        List<CourseEnrollment> enrollments = courseEnrollmentRepository.findByCourseId(course.getId());
        List<StudentDto> students  = new ArrayList<>();

        enrollments.forEach(enrollment ->{
            ResponseEntity<StudentDto> student = restTemplate.getForEntity(GET_STUDENT_BY_ID+enrollment.getStudentId(),StudentDto.class);
            students.add(student.getBody());
        });


        return CourseDetailsDto.builder()
                .courseName(course.getCourseName())
                .courseDescription(course.getCourseDescription())
                .courseReview(course.getCourseReview())
                .genre(course.getGenre())
                .authorName(course.getAuthorName())
                .teacher(teacher)
                .students(students)
                .build();
    }

    @Override
    public List<CourseResponseDto> getAllCourseByStudentId(Long id) {
        log.info("Fetching all enrolled courses by student");
        List<CourseEnrollment> enrollments = courseEnrollmentRepository.findByStudentId(id);
        List<Course> courses = new ArrayList<>();
        enrollments.forEach(enrollment -> {
            Optional<Course> course = courseRepository.findById(enrollment.getCourseId());
            if (course.isEmpty()){
                throw new CourseNotFoundException("course not found for id: "+ enrollment.getEnrollmentId());
            }
            courses.add(course.get());
        });
        return courseMapper.toCourseResponseDto(courses);
    }

    @Override
    public List<CourseResponseDto> getAllCourse() {
        log.info("Fetching all courses");
        return courseMapper.toCourseResponseDto(courseRepository.findAll());
    }

    @Override
    public List<CourseResponseDto> getAllCourseByTeacherId(Long id) {
        log.info("Fetching all courses by teacher for id: " + id);
        return courseMapper.toCourseResponseDto(courseRepository.findAllByTeacherId(id));
    }

    @Override
    public CourseResponseDto updateCourse(CourseUpdateDto courseUpdateDto) {
        log.info("Updating course for id: " + courseUpdateDto.getCourseId());
        courseAvailabilityCheck(courseUpdateDto.getCourseId());
        Course course = courseRepository.save(courseMapper.toCourse(courseUpdateDto));
        return courseMapper.toCourseResponseDto(course);
    }

    @Override
    public void removeCourseById(Long id) {
        log.info("Removing course for id: " + id);
        courseAvailabilityCheck(id);
        courseRepository.deleteById(id);
    }

    @Override
    public void enrollNewCourse(CourseEnrollDto courseEnrollDto) {
        log.info("Enrolling course by student, student id: " + courseEnrollDto.getStudentId() + " & course id: " + courseEnrollDto.getCourseId());
        boolean doesAlreadyEnrolled = courseEnrollmentRepository
                .existsByCourseIdAndStudentId(courseEnrollDto.getCourseId(), courseEnrollDto.getStudentId());
        if (doesAlreadyEnrolled) {
            log.warn("Already enrolled this course for student id: " + courseEnrollDto.getStudentId() + " & course id: " + courseEnrollDto.getCourseId());
            throw new CourseAlreadyEnrolledException("Opps! The course is already Enrolled by You.");
        }
        courseAvailabilityCheck(courseEnrollDto.getCourseId());
        courseEnrollmentRepository.save(courseMapper.toCourseEnrollment(courseEnrollDto));
    }

    private void courseAvailabilityCheck(Long id) {
        boolean doesExistCourse = courseRepository.existsById(id);
        if (!doesExistCourse) {
            log.warn("Course not found for id: " + id);
            throw new CourseNotFoundException("Course not found for id: " + id);
        }
    }
}
