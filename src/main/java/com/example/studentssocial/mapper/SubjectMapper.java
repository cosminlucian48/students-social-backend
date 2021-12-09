package com.example.studentssocial.mapper;


import com.example.studentssocial.dto.SubjectDto;
import com.example.studentssocial.dto.UserDetailsDto;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {
    public Subject mapSubjectDtoToSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        subject.setDescription(subjectDto.getDescription());
        return subject;
    }

    public SubjectDto mapSubjectToSubjectDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setName(subject.getName());
        subjectDto.setDescription(subject.getDescription());
        return subjectDto;
    }
}
