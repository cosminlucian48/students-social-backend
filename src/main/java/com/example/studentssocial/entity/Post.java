package com.example.studentssocial.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JoinColumn
    @ManyToOne()
    private User user;
    @JoinColumn
    @ManyToOne()
    private Subject subject;
    @Column(name="title")
    private String title;
    @Column(name = "text")
    private String text;
    @Column (name = "postDate")
    private Date postDate;
    @Column (name = "isSticky")
    private Boolean isSticky;
    @Column (name = "postType")
    private String postType;
//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    private List<Comments> comments;
}
