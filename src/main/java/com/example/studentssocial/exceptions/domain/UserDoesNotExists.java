package com.example.studentssocial.exceptions.domain;

public class UserDoesNotExists extends RuntimeException{
    public UserDoesNotExists(String message) {
        super(message);
    }
}
