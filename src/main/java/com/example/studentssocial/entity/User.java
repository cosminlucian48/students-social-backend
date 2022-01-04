package com.example.studentssocial.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "university")
    private String university;
    @Column(name = "faculty")
    private String faculty;
    @Column(name = "registrationDate")
    private Date registrationDate;
    @Column(name = "authorities")
    private String authorities;


    public User(String email, String password, String firstName, String lastName, String university, Date registrationDate, String authorities, String faculty) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.university = university;
        this.registrationDate = registrationDate;
        this.authorities = authorities;
        this.faculty = faculty;
    }

    public User() {
    }
}
