
package com.example.studentssocial.mapper;

        import com.example.studentssocial.dto.UserSubjectDto;
        import com.example.studentssocial.entity.User;
        import com.example.studentssocial.entity.UserSubject;
        import org.springframework.stereotype.Component;

@Component
public class UserSubjectMapper {
    public UserSubject mapUserSubjectDtoToUserSubject(UserSubjectDto userSubjectDto) {
        UserSubject userSubject = new UserSubject();

        userSubject.setId_user(userSubjectDto.getId_user());
        userSubject.setId_subject(userSubjectDto.getId_subject());
        userSubject.setRegistrationDate(userSubjectDto.getRegistrationDate());

       // userSubject.setUserSubject(userSubject);
        return userSubject;
    }

    public UserSubjectDto mapUserSubjectToUserSubjectDto(UserSubject userSubject) {
        UserSubjectDto userSubjectDto = new UserSubjectDto();

        userSubjectDto.setId_user(userSubject.getId_user());
        userSubjectDto.setId_subject(userSubject.getId_subject());
        userSubjectDto.setRegistrationDate(userSubject.getRegistrationDate());
        //userSubjectDto.setId(userSubject.getId());

        return userSubjectDto;
    }
}
