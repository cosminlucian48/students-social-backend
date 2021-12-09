package com.example.studentssocial.entity;


import lombok.Data;

import javax.persistence.*;

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


}
