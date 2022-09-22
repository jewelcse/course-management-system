package com.bjit.course.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class APIConstraints {

    public static final String APPLICATION_BASE_API = "api/v1/";
    public static final String TEACHER_SERVICE_BASE_URL = "http://localhost:8082/api/v1";
    public static final String STUDENT_SERVICE_BASE_URL = "http://localhost:8083/api/v1";
    public static final String GET_TEACHER_BY_ID = TEACHER_SERVICE_BASE_URL + "/teachers/";
    public static final String GET_STUDENT_BY_ID = STUDENT_SERVICE_BASE_URL + "/students/";
}
