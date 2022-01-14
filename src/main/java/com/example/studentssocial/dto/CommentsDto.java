package com.example.studentssocial.dto;

import com.example.studentssocial.entity.Post;
import lombok.Data;

import java.util.Date;

@Data
public class CommentsDto {
    private Long id;
    private Date commentsDate;
    private String text;
    private String email;
    private long postId;
    private long userId;
    private String commentType;
    private String profileImage;
    private String fileName;
}
