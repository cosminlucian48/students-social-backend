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
        import java.util.stream.Collectors;

//@RequiredArgsConstructor //constructor cu parametrii final
@Service
public class UserSubjectService {

    private final UserSubjectRepository userSubjectRepository;

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
}
