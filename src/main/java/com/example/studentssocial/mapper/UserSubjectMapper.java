
package com.example.studentssocial.mapper;

import com.example.studentssocial.dto.UserSubjectDto;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserSubject;
import com.example.studentssocial.repository.SubjectRepository;
import com.example.studentssocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor // generease constructor pentru atributele final
@Component
public class UserSubjectMapper {
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    public UserSubject mapUserSubjectDtoToUserSubject(UserSubjectDto userSubjectDto) {

        Subject subject = subjectRepository.findById(userSubjectDto.getSubjectId()).get();
        User user = userRepository.findById(userSubjectDto.getUserId()).get();
        UserSubject userSubject = new UserSubject();

        userSubject.setUser(user);
        userSubject.setSubject(subject);
        userSubject.setRegistrationDate(userSubjectDto.getRegistrationDate());

        return userSubject;
    }

    public UserSubjectDto mapUserSubjectToUserSubjectDto(UserSubject userSubject) {
        UserSubjectDto userSubjectDto = new UserSubjectDto();

        userSubjectDto.setSubjectId(userSubject.getSubject().getId());
        userSubjectDto.setUserId(userSubject.getUser().getId());
        userSubjectDto.setRegistrationDate(userSubject.getRegistrationDate());
        //userSubjectDto.setId(userSubject.getId());

        return userSubjectDto;
    }
}
