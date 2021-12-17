package com.example.studentssocial.mapper;


import com.example.studentssocial.dto.SubjectDto;
import com.example.studentssocial.entity.Subject;
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
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setDescription(subject.getDescription());
        return subjectDto;
    }
}
