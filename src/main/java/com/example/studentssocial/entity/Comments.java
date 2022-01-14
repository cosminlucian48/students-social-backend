package com.example.studentssocial.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
public class Comments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column (name = "commentsDate")
    private Date commentsDate;
    @Column(name = "text")
    private String text;
    @JoinColumn
    @ManyToOne()
    private Post post;
    @JoinColumn
    @ManyToOne()
    private User user;
    @Column(name="commentType")
    private String commentType;
    @Lob
    @Column(name = "fileName")
    private String fileName;

}
