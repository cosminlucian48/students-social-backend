package com.example.studentssocial.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserSubjectDto {
    private Long userId;
    private Long subjectId;
    private Date registrationDate;
}

