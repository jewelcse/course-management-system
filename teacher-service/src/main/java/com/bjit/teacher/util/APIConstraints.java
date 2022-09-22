package com.bjit.teacher.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class APIConstraints {
    public static final String APPLICATION_BASE_API = "api/v1/";
    public static final String COURSE_SERVICE_BASE_URL = "http://localhost:8081/api/v1";
    public static final String COURSE_CREATE_API = COURSE_SERVICE_BASE_URL + "/courses";
    public static final String GET_ALL_COURSE_BY_TEACHER_API = COURSE_SERVICE_BASE_URL + "/courses/teachers/";
    public static final String COURSE_UPDATE_API = COURSE_SERVICE_BASE_URL + "/courses/";
    public static final String COURSE_REMOVE_API = COURSE_SERVICE_BASE_URL + "/courses/";
    public static final String GET_SINGLE_COURSE_API = COURSE_SERVICE_BASE_URL + "/courses/";
    public static final String GET_ALL_COURSE_API = COURSE_SERVICE_BASE_URL + "/courses";
}
