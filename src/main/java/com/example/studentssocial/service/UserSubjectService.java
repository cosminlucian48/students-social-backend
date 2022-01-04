package com.example.studentssocial.service;

import com.example.studentssocial.dto.UserSettingsDto;
import com.example.studentssocial.dto.UserSubjectDto;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserSubject;
import com.example.studentssocial.mapper.UserSubjectMapper;
import com.example.studentssocial.repository.SubjectRepository;
import com.example.studentssocial.repository.UserRepository;
import com.example.studentssocial.repository.UserSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

//@RequiredArgsConstructor //constructor cu parametrii final
@Service
public class UserSubjectService {

    private final UserSubjectRepository userSubjectRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    private final UserSubjectMapper userSubjectMapperDetails;

    @Autowired
    public UserSubjectService(UserSubjectRepository userSubjectRepository, UserRepository userRepository, SubjectRepository subjectRepository, UserSubjectMapper userSubjectMapperDetails) {
        this.userSubjectRepository = userSubjectRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        //this.userDetailsRepository = userDetailsRepository;
        this.userSubjectMapperDetails = userSubjectMapperDetails;
    }

    public List<UserSubjectDto> getAllUserSubjects() {
        List<UserSubject> userSubject = new ArrayList<>();

        userSubjectRepository.findAll().iterator().forEachRemaining(userSubject::add);
        return userSubject.stream().map(elem -> userSubjectMapperDetails.mapUserSubjectToUserSubjectDto(elem)).collect(Collectors.toList());

    }


    public UserSubjectDto saveUserSubject(UserSubjectDto userSubjectDto) {
        UserSubject userSubject = userSubjectMapperDetails.mapUserSubjectDtoToUserSubject(userSubjectDto);
        UserSubject savedUserSubject = userSubjectRepository.save(userSubject);
        return userSubjectMapperDetails.mapUserSubjectToUserSubjectDto(savedUserSubject);
    }

    public UserSubject getUserSubjectById(Long id) {

        Optional<UserSubject> optionalUserSubject = userSubjectRepository.findById(id);
        return optionalUserSubject.orElse(null);
    }

    public void deleteUserSubject(Long id) {
        Optional<UserSubject> userSubject = userSubjectRepository.findById(id);
        if (userSubject.isPresent()) {
            userSubjectRepository.delete(userSubject.get());
        } else {
            throw new NoSuchElementException(String.valueOf(id));
        }
    }


}
