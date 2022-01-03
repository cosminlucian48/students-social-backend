package com.example.studentssocial.service;

import com.example.studentssocial.dto.UserDto;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.enums.RoleType;
import com.example.studentssocial.enums.UserType;
import com.example.studentssocial.entity.UserSubject;
import com.example.studentssocial.exceptions.domain.UserAlreadyExistsExceptions;
import com.example.studentssocial.exceptions.domain.UserDoesNotExists;
import com.example.studentssocial.mapper.UserMapper;
import com.example.studentssocial.repository.UserRepository;
import com.example.studentssocial.util.ApplicationUtil;
import com.example.studentssocial.repository.UserSubjectRepository;
import com.example.studentssocial.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

//@RequiredArgsConstructor //constructor cu parametrii final
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserSubjectRepository userSubjectRepository;

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final MyUserDetailsService userDetailsService;

    private final JwtUtil jwtTokenUtil;

    @Autowired
    public UserService(UserRepository userRepository, UserSubjectRepository userSubjectRepository, UserMapper userMapperDetails, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, MyUserDetailsService userDetailsService, JwtUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.userSubjectRepository = userSubjectRepository;
        this.userMapper = userMapperDetails;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }


    public UserDto registerUser(UserDto userDto) {
        if (userAlreadyExists(userDto.getEmail())) {
            throw new UserAlreadyExistsExceptions("User already exists!");
        }
        return parseAndCreateUser(userDto, UserType.USER);

    }

    private UserDto parseAndCreateUser(UserDto userDto, UserType userType) {
        User user = userMapper.mapUserDtoToUser(userDto);
        user.setAuthorities(ApplicationUtil.fromListToString(getAutoritiesByUserType(userType)));

        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepository.save(user);
        return userMapper.mapUserToUserDto(savedUser);
    }

    private List<String> getAutoritiesByUserType(UserType userType) {
        switch (userType) {
            case USER:
                return Arrays.asList(RoleType.USER.toString());
            case MODERATOR:
                return Arrays.asList(RoleType.MODERATOR.toString());
            case ADMIN:
                return Arrays.asList(RoleType.ADMIN.toString());
        }
        return new ArrayList<>();
    }



    public String loginUserAndReturnJWT(UserDto userDto) {
        if (!userAlreadyExists(userDto.getEmail())) {
            throw new UserDoesNotExists("Incorrect username or password");
        }
        //try catch
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(userDto.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return jwt;
//        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }

    public boolean userAlreadyExists(String userEmail) {
        List<User> users = getUsersByEmail(userEmail);
        if (users.isEmpty()) {
            return false;
        }
        return true;
    }

    public List<User> getUsersByEmail(String userEmail) {
        List<User> users = userRepository.findUserByEmail(userEmail);
        return users;
    }


    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }


    public User updateUser(Long id, User user) {

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        } else {
            throw new NoSuchElementException(String.valueOf(user));
        }
    }

    public List<User> getUsersBySubjectId(Long subjectId)
    {
        List<UserSubject> usersSubject = new ArrayList<>();
        List<User> users = new ArrayList<>();
        userSubjectRepository.findAllBySubjectId(subjectId).iterator().forEachRemaining(usersSubject::add);
        for (UserSubject userSubject : usersSubject)
        {
            users.add(this.getUserById(userSubject.getUser().getId()));
        }
        return users;
    }


}

