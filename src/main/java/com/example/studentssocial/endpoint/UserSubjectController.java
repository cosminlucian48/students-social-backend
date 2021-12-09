package com.example.studentssocial.endpoint;

        import com.example.studentssocial.dto.UserSubjectDto;
        import com.example.studentssocial.entity.UserSubject;
        import com.example.studentssocial.service.UserSubjectService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping(value = "/user_subject")
public class UserSubjectController {

    private final UserSubjectService userSubjectService;

    @Autowired
    public UserSubjectController(UserSubjectService userSubjectService) {
        this.userSubjectService = userSubjectService;
    }

    @GetMapping
    public List<UserSubject> getAllUserSubjects() {
        return userSubjectService.getAllUserSubjects();
    }



    @PostMapping
    public UserSubjectDto postUser(@RequestBody UserSubjectDto userSubjectDto) {
        return userSubjectService.saveUserSubject(userSubjectDto);
    }

}
