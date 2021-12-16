package com.example.studentssocial.endpoint;

import com.example.studentssocial.dto.UserDetailsDto;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserDetails;
import com.example.studentssocial.repository.UserDetailsRepository;
import com.example.studentssocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/user-details")
public class UserDetailsController {

    private final UserService userService;
    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailsController(UserService userService, UserDetailsRepository userDetailsRepository, UserDetailsRepository userDetailsRepository1){
        this.userService = userService;
        this.userDetailsRepository = userDetailsRepository;
    }
    @GetMapping
    public List<UserDetailsDto> getAllUserDetails(){
        return userService.getAllUserDetails();
    }

    @GetMapping(value="/{id}")
    public UserDetails getUserDetailsById(@PathVariable Long id){
        return userService.getUserDetailsById(id);
    }

    @PutMapping(value = "/{id}")
    public UserDetails updateUserDetails(@PathVariable(name = "id") Long id,
                                         @RequestBody UserDetails userDetails) {

        return userService.updateUser(id, userDetails);
    }

}
