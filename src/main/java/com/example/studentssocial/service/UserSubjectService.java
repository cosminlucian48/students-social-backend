package com.example.studentssocial.service;

import com.example.studentssocial.dto.UserSubjectDto;
import com.example.studentssocial.entity.UserSubject;
import com.example.studentssocial.mapper.UserSubjectMapper;
import com.example.studentssocial.repository.UserSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

//@RequiredArgsConstructor //constructor cu parametrii final
@Service
public class UserSubjectService {

    private final UserSubjectRepository userSubjectRepository;
    //private final UserDetailsRepository userDetailsRepository;

    private final UserSubjectMapper userSubjectMapperDetails;

    @Autowired
    public UserSubjectService(UserSubjectRepository userSubjectRepository, UserSubjectMapper userSubjectMapperDetails) {
        this.userSubjectRepository = userSubjectRepository;
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
