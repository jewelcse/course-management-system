package com.bjit.teacher.serviceImpl;

import com.bjit.teacher.dto.request.CourseCreateDto;
import com.bjit.teacher.dto.request.CourseUpdateDto;
import com.bjit.teacher.dto.response.CourseResponseDto;
import com.bjit.teacher.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.bjit.teacher.util.APIConstraints.*;

@RequiredArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {
    private static final Log log = LogFactory.getLog(CourseServiceImpl.class);
    private final RestTemplate restTemplate;

    @Override
    public void saveCourse(CourseCreateDto course) {
        log.info("Creating a new course by teacher");
        ResponseEntity<String> res = restTemplate.postForEntity(COURSE_CREATE_API, course, String.class);
        log.info(res.getBody());
    }

    @Override
    public CourseResponseDto getCourseById(Long id) {
        log.info("Fetching course for id: " + id);
        ResponseEntity<CourseResponseDto> course = restTemplate.getForEntity(GET_SINGLE_COURSE_API+id,CourseResponseDto.class);
        return course.getBody();
    }

    @Override
    public List<CourseResponseDto> getAllCourseBYTeacherIds(Long id) {
        log.info("Fetching all courses");
        ResponseEntity<CourseResponseDto[]> response
                = restTemplate.getForEntity(GET_ALL_COURSE_BY_TEACHER_API+id,CourseResponseDto[].class);
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(response.getBody())));
    }

    @Override
    public CourseResponseDto updateCourse(CourseUpdateDto course) {
        log.info("Updating course for name:" + course.getCourseName());
        //todo: perform external call for updating the course details
        return null;
    }

    @Override
    public void removeCourseById(Long id) {
        log.info("Removing course for id: " + id);
        restTemplate.delete(COURSE_REMOVE_API + id);
    }
}
