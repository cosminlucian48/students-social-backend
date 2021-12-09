package com.example.studentssocial.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserSubjectDto {
    private Long id_user;
    private Long id_subject;
    private Date registrationDate;
}

