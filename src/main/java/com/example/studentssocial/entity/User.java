package com.example.studentssocial.entity;

import com.example.studentssocial.constants.SecurityConstant;
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
    @Lob
    @Column(name = "profileImage",columnDefinition = "varchar(10000000) DEFAULT '" + SecurityConstant.DEFAULT_PROFILE_IMAGE+"'" )
    private String profileImage;

    @Column(name = "tagEmail",columnDefinition="BOOLEAN DEFAULT true")
    private Boolean tagEmail;
    @Column(name = "commentEmail",columnDefinition="BOOLEAN DEFAULT true")
    private Boolean commentEmail;
    @Column(name = "postEmail",columnDefinition="BOOLEAN DEFAULT true")
    private Boolean postEmail;
//    byte[] profileImage;


    public User(String email, String password, String firstName, String lastName, String university,
                Date registrationDate, String authorities, String faculty, String profileImage) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.university = university;
        this.registrationDate = registrationDate;
        this.authorities = authorities;
        this.faculty = faculty;
        this.profileImage = profileImage;
    }

    public User() {
    }
}
