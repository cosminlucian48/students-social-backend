package com.example.studentssocial.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_id")
    private Long userID;
    @Column(name = "subject_id")
    private Long subjectID;
    @Column(name="title")
    private String title;
    @Column(name = "text")
    private String text;
    @Column (name = "postDate")
    private Date postDate;
    @Column (name = "isSticky")
    private Boolean isSticky;
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="post_id",referencedColumnName = "id")

    private Post post;
}
