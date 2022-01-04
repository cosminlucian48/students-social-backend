package com.example.studentssocial.dto;

import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserSettingsDto {
   private User user;
   private List<Subject> subjects;
}
