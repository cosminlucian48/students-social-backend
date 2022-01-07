package com.example.studentssocial.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @JoinColumn
    @ManyToOne()
    private User user;

//    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
//    private List<Post> posts;
//
//    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
//    private List<UserSubject> userSubjects;


}
