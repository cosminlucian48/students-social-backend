package com.example.studentssocial.endpoint;

import com.example.studentssocial.dto.UserDetailsDto;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() { return userService.getAllUsers();
    }
    @GetMapping(value="/id")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
//
//    @PatchMapping


    @PostMapping
    public UserDetailsDto postUser(@RequestBody UserDetailsDto userDto) {
        return userService.saveUser(userDto);
    }

}
