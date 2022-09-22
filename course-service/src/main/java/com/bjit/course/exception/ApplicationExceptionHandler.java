package com.bjit.course.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<List<String>> processUnmergeException(final MethodArgumentNotValidException ex) {

        List<String> list = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        return new ResponseEntity<>(list, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> courseNotFoundException(CourseNotFoundException exception) {
        return new ResponseEntity<>(new ExceptionResponse(exception.getMessage(), "404"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseAlreadyEnrolledException.class)
    public ResponseEntity<ExceptionResponse> courseAlreadyEnrolledException(CourseAlreadyEnrolledException exception) {
        return new ResponseEntity<>(new ExceptionResponse(exception.getMessage(), "500"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class ExceptionResponse {
        private String message;
        private String responseCode;

    }
}
