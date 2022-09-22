package com.bjit.student.exception;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(String message){
        super(message);
    }
}
