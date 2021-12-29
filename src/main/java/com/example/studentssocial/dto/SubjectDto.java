package com.example.studentssocial.dto;


import lombok.Data;

@Data
public class SubjectDto {
    private Long id;
    private String name;
    private String description;
    private Long userId;
}
