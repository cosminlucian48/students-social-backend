package com.example.studentssocial.dto;

import lombok.Data;

import javax.persistence.Column;
import java.io.File;
import java.util.Date;
import java.util.List;

@Data
public class PostDto {
    private Long id;
    private Long userId;
    private String email;
    private Long subjectId;
    private Date postDate;
    private String title;
    private String text;
    private Boolean isSticky;
    private String postType;
    private String profileImage;
    private String fileName;
    private String realFileName;
//    private List<File> files;
}
