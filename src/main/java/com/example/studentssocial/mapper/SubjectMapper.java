package com.example.studentssocial.mapper;


import com.example.studentssocial.dto.SubjectDto;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SubjectMapper {
    private final UserRepository userRepository;

    public Subject mapSubjectDtoToSubject(SubjectDto subjectDto) {
        User user = userRepository.findById(subjectDto.getUserId()).get();
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        subject.setDescription(subjectDto.getDescription());
        subject.setUser(user);
        return subject;
    }

    public SubjectDto mapSubjectToSubjectDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setDescription(subject.getDescription());
        subjectDto.setUserId(subject.getUser().getId());
        return subjectDto;
    }
}
