package com.example.studentssocial.endpoint;

import com.example.studentssocial.constants.SecurityConstant;
import com.example.studentssocial.dto.UserDto;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.enums.UserType;
import com.example.studentssocial.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(origins = {"http://localhost:4200"})
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

    @GetMapping(value="/user-type")
    public List<User> getUsersByUserType(@RequestParam String userType) {
        return userService.getUserByUserType(userType);
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


    @GetMapping(value = "/subject/{subjectId}")
    public List<User> getUserBySubjectId(@PathVariable("subjectId") Long subjectId){return userService.getUsersBySubjectId(subjectId);}
    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/email")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email);
    }

    @PutMapping(value = "/{id}")
    public User updateUser(@PathVariable(name = "id") Long id,
                           @RequestBody User user) {

        return userService.updateUser(id, user);
    }
    @PutMapping("/new-password")
    public UserDto changeUserPassword(@RequestBody UserDto userDto){
        return userService.changeUserPassword(userDto.getPassword(),userDto.getEmail());
    }
}








