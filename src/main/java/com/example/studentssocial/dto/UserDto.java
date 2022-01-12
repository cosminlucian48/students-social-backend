package com.example.studentssocial.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String university;
    private Date registrationDate;
    private String authorities;
    private String faculty;
    private String profileImage;
    private Boolean tagEmail;
    private Boolean commentEmail;
    private Boolean postEmail;
}
