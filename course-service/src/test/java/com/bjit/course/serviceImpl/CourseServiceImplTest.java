package com.bjit.course.serviceImpl;

import com.bjit.course.dto.request.CourseCreateDto;
import com.bjit.course.dto.request.CourseEnrollDto;
import com.bjit.course.dto.request.CourseUpdateDto;
import com.bjit.course.dto.response.CourseResponseDto;
import com.bjit.course.entity.Course;
import com.bjit.course.entity.CourseEnrollment;
import com.bjit.course.exception.CourseNotFoundException;
import com.bjit.course.mapper.CourseMapper;
import com.bjit.course.repository.CourseEnrollmentRepository;
import com.bjit.course.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @InjectMocks
    private CourseServiceImpl courseService;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseEnrollmentRepository courseEnrollmentRepository;

    @Mock
    private CourseMapper courseMapper;


    private CourseCreateDto courseCreateDto= null;
    private CourseEnrollDto courseEnrollDto = null;
    private CourseEnrollment courseEnrollment = null;
    private CourseResponseDto courseResponseDto = null;
    private CourseUpdateDto courseUpdateDto = null;
    private Course course = null;
    private List<CourseResponseDto> courseResponseDtos = null;
    private List<Course> courses = new ArrayList<>();

    @BeforeEach
    void setUp() {
        courseCreateDto = new CourseCreateDto("Artificial Intelligence","AI Course for CSE dept.","AI","Computer Science",10L);
        courseEnrollDto = new CourseEnrollDto(1L,2L);
        courseEnrollment = new CourseEnrollment(1L,1L,2L);
        courseResponseDto = new CourseResponseDto("Artificial Intelligence","AI Course for CSE dept.","AI","Computer Science",10L,2.5);
        courseUpdateDto = new CourseUpdateDto(1L,"AI","AI","Computer Science","Computer Science",10L);
        course = new Course(1L, "Artificial Intelligence","AI Course for CSE dept.","AI","Computer Science",10L,2.5);
        courses = Arrays.asList(course,course);
        courseResponseDtos = Arrays.asList(courseResponseDto,courseResponseDto);

    }

    @Test
    @DisplayName("creating a new course test()")
    void createNewCourseTest(){
        when(courseMapper.toCourse(courseCreateDto)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(course);

        courseService.saveCourse(courseCreateDto);

        verify(courseRepository).save(course);
    }


    @Test
    @DisplayName("get all courses test()")
    void getAllCourseTest(){
        when(courseRepository.findAll()).thenReturn(courses);
        when(courseMapper.toCourseResponseDto(courses)).thenReturn(courseResponseDtos);

        assertEquals(2, courseService.getAllCourse().size());

    }


    @Test
    @DisplayName("get all courses by teacher")
    void getAllCourseByTeacherTest(){
        long id = 10L;
        when(courseRepository.findAllByTeacherId(id)).thenReturn(courses);
        when(courseMapper.toCourseResponseDto(courses)).thenReturn(courseResponseDtos);

        assertEquals(2,courseService.getAllCourseByTeacherId(id).size());
    }

    @Test
    @DisplayName("get course by course id")
    void getCourseByIdTest(){
        long id = 1L;
        when(courseRepository.existsById(id)).thenReturn(true);
        when(courseRepository.findById(id)).thenReturn(Optional.of(course));
        when(courseMapper.toCourseResponseDto(course)).thenReturn(courseResponseDto);

        assertEquals("Artificial Intelligence",courseService.getCourseById(id).getCourseName());
    }

    @Test
    @DisplayName("course not found exception is thrown test()")
    void courseNotFoundExceptionIsThrownTest() {
        long courseId = 1L;
        when(courseRepository.existsById(courseId)).thenReturn(false);
        assertThrows(CourseNotFoundException.class, () -> courseService.getCourseById(courseId));
    }

    @Test
    @DisplayName("update course test()")
    void updateCourseTest(){
        long id = 1L;
        when(courseRepository.existsById(id)).thenReturn(true);
        when(courseMapper.toCourse(courseUpdateDto)).thenReturn(course);
        when(courseRepository.save(course)).thenReturn(course);
        when(courseMapper.toCourseResponseDto(course)).thenReturn(courseResponseDto);

        assertEquals(courseResponseDto,courseService.updateCourse(courseUpdateDto));

    }

    @Test
    @DisplayName("remove course by id test()")
    void removeCourseByIdTest(){
        long id = 1L;
        when(courseRepository.existsById(id)).thenReturn(true);

        courseService.removeCourseById(id);
        verify(courseRepository).deleteById(id);
    }


    @Test
    @DisplayName("enroll course by student test()")
    void enrollNewCourseByStudentTest(){
        long courseId = 1L;
        long studentId = 2L;

        when(courseEnrollmentRepository.existsByCourseIdAndStudentId(courseId, studentId)).thenReturn(false);
        when(courseRepository.existsById(courseId)).thenReturn(true);
        when(courseMapper.toCourseEnrollment(courseEnrollDto)).thenReturn(courseEnrollment);

        courseService.enrollNewCourse(courseEnrollDto);

        verify(courseEnrollmentRepository).save(courseEnrollment);
    }
}