package com.example.studentssocial.endpoint;

import com.example.studentssocial.dto.UserDetailsDto;
import com.example.studentssocial.entity.UserDetails;
import com.example.studentssocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user-details")
public class UserDetailsController {

    private final UserService userService;

    @Autowired
    public UserDetailsController(UserService userService){
        this.userService = userService;
    }
    @GetMapping
    public List<UserDetailsDto> getAllUserDetails(){
        return userService.getAllUserDetails();
    }

}
