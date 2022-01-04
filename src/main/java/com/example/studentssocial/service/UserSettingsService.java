package com.example.studentssocial.service;

import com.example.studentssocial.dto.UserSettingsDto;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserSubject;
import com.example.studentssocial.mapper.UserSubjectMapper;
import com.example.studentssocial.repository.UserRepository;
import com.example.studentssocial.repository.UserSubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserSettingsService {

    private final UserSubjectRepository userSubjectRepository;
    private final UserRepository userRepository;


    public UserSettingsService(UserSubjectRepository userSubjectRepository, UserRepository userRepository) {
        this.userSubjectRepository = userSubjectRepository;
        this.userRepository = userRepository;
    }


    public void saveUserSettings(UserSettingsDto userSettingsDto) {
        User user = userSettingsDto.getUser();
        UserSubject auxUserSubject;
        List<Subject> subjects = userSettingsDto.getSubjects();
        userSubjectRepository.deleteAllByUserId(user.getId());
        for (Subject subject : subjects) {
            auxUserSubject = new UserSubject();
            auxUserSubject.setUser(user);
            auxUserSubject.setSubject(subject);
            auxUserSubject.setRegistrationDate(new Date());
            userSubjectRepository.save(auxUserSubject);


        }
    }

    public UserSettingsDto getUserSettings(Long userId) {
        List<UserSubject> userSubjects = userSubjectRepository.findAllByUserId(userId);
        List<Subject> subjects = new ArrayList<>();
        Optional<User> user = userRepository.findById(userId);
        for (UserSubject userSubject : userSubjects) {
            subjects.add(userSubject.getSubject());
        }
        UserSettingsDto userSettingsDto = new UserSettingsDto();
        if (user.isPresent()) {
            userSettingsDto.setUser(user.get());
            userSettingsDto.setSubjects(subjects);
            return userSettingsDto;
        }
        return userSettingsDto;
    }

    public UserSettingsDto getUserSettingsByEmail(String email){
        User user  = userRepository.findUserByEmail(email).get(0);
        return this.getUserSettings(user.getId());
//        List<UserSubject> userSubjects = userSubjectRepository.findAllByUserId(user.getId());
//        List<Subject> subjects = new ArrayList<>();
//        for(UserSubject userSubject:userSubjects){
//            subjects.add(userSubject.getSubject());
//        }
//        UserSettingsDto userSettingsDto = new User
    }
}
