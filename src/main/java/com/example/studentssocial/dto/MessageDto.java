package com.example.studentssocial.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MessageDto {
    private Long id;
    private Date date;
    private String text;
    private long postId;
    private String postTitle;
    private long userId;
    private String email;
    private Long subjectId;
    private String lastName;
    private String firstName;
    private String university;
    private String faculty;
}
