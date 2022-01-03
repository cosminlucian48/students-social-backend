package com.example.studentssocial.endpoint;


import com.example.studentssocial.dto.SubjectDto;
import com.example.studentssocial.entity.Subject;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.service.SubjectService;
import com.example.studentssocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/subject")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SubjectController {
    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public List<SubjectDto> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping
    public SubjectDto postSubject(@RequestBody SubjectDto subjectDto){
        return subjectService.saveSubject(subjectDto);}

    @GetMapping(value="/{id}")
    public Subject getSubjectById(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
    }
}
