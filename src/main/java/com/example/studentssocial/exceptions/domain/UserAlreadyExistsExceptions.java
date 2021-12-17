package com.example.studentssocial.exceptions.domain;

public class UserAlreadyExistsExceptions extends RuntimeException{
    public UserAlreadyExistsExceptions(String message) {
        super(message);
    }
}
