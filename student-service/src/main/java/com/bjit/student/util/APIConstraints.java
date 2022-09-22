package com.bjit.student.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class APIConstraints {
    public static final String APPLICATION_BASE_API = "api/v1/";
    public static final String COURSE_SERVICE_BASE_URL = "http://localhost:8081/api/v1";
    public static final String COURSE_ENROLLMENT_API = COURSE_SERVICE_BASE_URL + "/courses/enroll";
    public static final String GET_ENROLLED_COURSES_API = COURSE_SERVICE_BASE_URL + "/courses/students/";
}
