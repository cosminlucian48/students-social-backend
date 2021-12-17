package com.example.studentssocial.endpoint;

import com.example.studentssocial.constants.SecurityConstant;
import com.example.studentssocial.dto.UserDto;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.service.MyUserDetailsService;
import com.example.studentssocial.service.UserService;
import com.example.studentssocial.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> createAuthenticationToken(@RequestBody UserDto userDto) {
        String jwt = userService.loginUserAndReturnJWT(userDto);
        return new ResponseEntity<>("", getJwtHeader(jwt), HttpStatus.OK);
    }

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }


    private HttpHeaders getJwtHeader(String jwt) {

        HttpHeaders headers = new HttpHeaders();

        headers.add(SecurityConstant.JWT_TOKEN_HEADER, jwt);

        headers.add("Access-Control-Expose-Headers", SecurityConstant.JWT_TOKEN_HEADER);

        return headers;

    }
}


