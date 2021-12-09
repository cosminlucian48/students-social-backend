
package com.example.studentssocial.mapper;

import com.example.studentssocial.dto.SubjectDto;
import com.example.studentssocial.dto.UserSubjectDto;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserDetails;
import com.example.studentssocial.entity.UserSubject;
import com.example.studentssocial.repository.SubjectRepository;
import com.example.studentssocial.repository.UserDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor // generease constructor pentru atributele final
@Component
public class UserSubjectMapper {
    private final UserDetailsRepository userDetailsRepository;
    private final SubjectRepository subjectRepository;

    public UserSubject mapUserSubjectDtoToUserSubject(UserSubjectDto userSubjectDto) {

        Subject subject = subjectRepository.findById(userSubjectDto.getSubjectId()).get();
        UserDetails userDetails = userDetailsRepository.findById(userSubjectDto.getUserDetailsId()).get();
        UserSubject userSubject = new UserSubject();

        userSubject.setUserDetails(userDetails);
        userSubject.setSubject(subject);

        userSubject.setRegistrationDate(userSubjectDto.getRegistrationDate());

        // userSubject.setUserSubject(userSubject);
        return userSubject;
    }

    public UserSubjectDto mapUserSubjectToUserSubjectDto(UserSubject userSubject) {
        UserSubjectDto userSubjectDto = new UserSubjectDto();

        userSubjectDto.setSubjectId(userSubject.getSubject().getId());
        userSubjectDto.setUserDetailsId(userSubject.getUserDetails().getId());
        userSubjectDto.setRegistrationDate(userSubject.getRegistrationDate());
        //userSubjectDto.setId(userSubject.getId());

        return userSubjectDto;
    }
}
