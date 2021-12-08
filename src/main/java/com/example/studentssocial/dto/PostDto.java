package com.example.studentssocial.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
    private Long id;
    private Long userID;
    private Long subjectID;
    private Date postDate;
    private String title;
    private String text;
    private Boolean isSticky;
}
