package com.example.studentssocial.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.mapping.Join;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "user_subject")
public class UserSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JoinColumn
    @ManyToOne()
    private User user;
    @JoinColumn
    @ManyToOne()
    private Subject subject;
    @Column(name = "registrationDate")
    private Date registrationDate;


}
