package com.example.studentssocial.service;


import com.example.studentssocial.dto.SubjectDto;
import com.example.studentssocial.dto.UserDetailsDto;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserDetails;
import com.example.studentssocial.mapper.SubjectMapper;
import com.example.studentssocial.mapper.UserDetailsMapper;
import com.example.studentssocial.repository.SubjectRepository;
import com.example.studentssocial.repository.UserDetailsRepository;
import com.example.studentssocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    public List<Subject> getAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjectRepository.findAll().iterator().forEachRemaining(subjects::add);
        return subjects;
    }


    public SubjectDto saveSubject(SubjectDto subjectDto) {
        Subject subject = subjectMapper.mapSubjectDtoToSubject(subjectDto);
        Subject savedSubject = subjectRepository.save(subject);
        return subjectMapper.mapSubjectToSubjectDto(savedSubject);
    }

    public Subject getSubjectById(Long id) {

        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        return optionalSubject.orElse(null);
    }
}
