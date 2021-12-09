package com.example.studentssocial.service;

        import com.example.studentssocial.dto.UserSubjectDto;
        import com.example.studentssocial.entity.UserSubject;
        import com.example.studentssocial.entity.UserSubject;
        import com.example.studentssocial.mapper.UserSubjectMapper;
        import com.example.studentssocial.repository.UserSubjectRepository;
        import com.example.studentssocial.repository.UserSubjectRepository;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Service;

        import java.util.ArrayList;
        import java.util.List;

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

    public List<UserSubject> getAllUserSubjects() {
        List<UserSubject> userSubjects = new ArrayList<>();
        userSubjectRepository.findAll().iterator().forEachRemaining(userSubjects::add);
        return userSubjects;
    }


    public UserSubjectDto saveUserSubject(UserSubjectDto userSubjectDto) {
        UserSubject userSubject = userSubjectMapperDetails.mapUserSubjectDtoToUserSubject(userSubjectDto);
        UserSubject savedUserSubject = userSubjectRepository.save(userSubject);
        return userSubjectMapperDetails.mapUserSubjectToUserSubjectDto(savedUserSubject);
    }
}
