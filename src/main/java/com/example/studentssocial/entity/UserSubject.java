package com.example.studentssocial.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_subject")
public class UserSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "id_user")
    private Long id_user;

    @Column(name = "id_subject")
    private Long id_subject;
    @Column(name = "registrationDate")
    private Date registrationDate;
}
