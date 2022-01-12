package com.example.studentssocial.dto;

import lombok.Data;

@Data
public class EmailFieldsDto {
    private String text;
    private String userEmail;
    private String taggerEmail;
    private Long subjectId;
    private Boolean tagChecked;
    private String subjectName;
    private String textType;
    private String postTitle;
    private String lastName;
    private String firstName;
    private String university;
    private String faculty;
}
