package com.bjit.course.exception;

public class CourseAlreadyEnrolledException extends RuntimeException {
    public CourseAlreadyEnrolledException(String message) {
        super(message);
    }
}
