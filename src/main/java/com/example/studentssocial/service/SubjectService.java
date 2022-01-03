package com.example.studentssocial.service;


import com.example.studentssocial.dto.SubjectDto;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.mapper.SubjectMapper;
import com.example.studentssocial.repository.SubjectRepository;
import com.example.studentssocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    private final SubjectMapper subjectMapper;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.subjectMapper = subjectMapper;
    }

    public List<SubjectDto> getAllSubjects() {
        List<SubjectDto> subjectsDto = new ArrayList<>();
        List<Subject> subjects = new ArrayList<>();
        subjectRepository.findAll().iterator().forEachRemaining(subjects::add);
        for(Subject subject: subjects){
            subjectsDto.add(subjectMapper.mapSubjectToSubjectDto(subject));
        }
        return subjectsDto;
    }


    public SubjectDto saveSubject(SubjectDto subjectDto) {
        List<User> users = userRepository.findUserByEmail(subjectDto.getUserEmail());
        if(!users.isEmpty()){
            subjectDto.setUserId(users.get(0).getId());
        }
        Subject subject = subjectMapper.mapSubjectDtoToSubject(subjectDto);
        Subject savedSubject = subjectRepository.save(subject);
        return subjectMapper.mapSubjectToSubjectDto(savedSubject);
    }

    public Subject getSubjectById(Long id) {

        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        return optionalSubject.orElse(null);
    }

    public void deleteSubject(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        if (subject.isPresent()) {
            subjectRepository.delete(subject.get());
        }
    }
}
