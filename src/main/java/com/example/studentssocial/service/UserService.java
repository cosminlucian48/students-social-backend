package com.example.studentssocial.service;

import com.example.studentssocial.constants.SecurityConstant;
import com.example.studentssocial.dto.UserDto;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.enums.RoleType;
import com.example.studentssocial.enums.UserEmailOptions;
import com.example.studentssocial.enums.UserType;
import com.example.studentssocial.entity.UserSubject;
import com.example.studentssocial.exceptions.domain.UserAlreadyExistsExceptions;
import com.example.studentssocial.exceptions.domain.UserDoesNotExists;
import com.example.studentssocial.mail.EmailService;
import com.example.studentssocial.mapper.MessageMapper;
import com.example.studentssocial.mapper.UserMapper;
import com.example.studentssocial.repository.UserRepository;
import com.example.studentssocial.repository.UserSubjectRepository;
import com.example.studentssocial.util.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.List;

//@RequiredArgsConstructor //constructor cu parametrii final
@Service
public class UserService {

    private final SendEmailService sendEmailService;

    private final UserRepository userRepository;
    private final UserSubjectRepository userSubjectRepository;

    private final UserMapper userMapper;
    private final MessageMapper messageMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final MyUserDetailsService userDetailsService;

    private final JwtUtil jwtTokenUtil;

    @Autowired
    public UserService(SendEmailService sendEmailService1, UserRepository userRepository,
                       UserSubjectRepository userSubjectRepository, UserMapper userMapperDetails,
                       MessageMapper messageMapper, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager,
                       MyUserDetailsService userDetailsService, JwtUtil jwtTokenUtil) {
        this.messageMapper = messageMapper;
        this.sendEmailService = sendEmailService1;

        this.userRepository = userRepository;
        this.userSubjectRepository = userSubjectRepository;
        this.userMapper = userMapperDetails;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    public List<User> getUserByUserType(String userType) {
        List<User> users = new ArrayList<>();
        userRepository.findUsersByAuthorities(userType).iterator().forEachRemaining(users::add);
//        userRepository.findAllByAuthorities(userType).iterator().forEachRemaining(users::add);
        return users;
    }


    public UserDto registerUser(UserDto userDto) {
        if (userAlreadyExists(userDto.getEmail())) {
            throw new UserAlreadyExistsExceptions("User already exists!");
        }
        if (userDto.getAuthorities().equals(UserType.USER.toString())) {
            return parseAndCreateUser(userDto, UserType.USER);
        } else if (userDto.getAuthorities().equals(UserType.ADMIN.toString())) {
            return parseAndCreateUser(userDto, UserType.ADMIN);
        } else {
            return parseAndCreateUser(userDto, UserType.MODERATOR);
        }

    }

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserDto parseAndCreateUser(UserDto userDto, UserType userType) {
        logger.info("test {}", userDto.getProfileImage());
        User user = userMapper.mapUserDtoToUser(userDto);
        user.setAuthorities(getAutoritiesByUserType(userType));

        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        if (user.getProfileImage().equals("")) {
            user.setProfileImage(SecurityConstant.DEFAULT_PROFILE_IMAGE);
        }
        user.setCommentEmail(Boolean.TRUE);
        user.setTagEmail(Boolean.TRUE);
        user.setPostEmail(Boolean.TRUE);
        User savedUser = userRepository.save(user);

        Thread thread = new Thread(() -> sendEmailService.verifyAndSendEmail(messageMapper.fromUserToMessageDto(savedUser), UserEmailOptions.REGISTER));
        thread.start();
//        this.emailThreadService.sendEmailToRegisteredUser(user);

        return userMapper.mapUserToUserDto(savedUser);
    }

    private String getAutoritiesByUserType(UserType userType) {
        switch (userType) {
            case USER:
                return RoleType.USER.toString();
            case MODERATOR:
                return RoleType.MODERATOR.toString();
            case ADMIN:
                return RoleType.ADMIN.toString();
        }
        return "";
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

    public UserDto getUserByEmail(String userEmail) {
        List<User> users = userRepository.findUserByEmail(userEmail);
        if (users.size() == 1) {
            return userMapper.mapUserToUserDto(users.get(0));
        }
        return null;

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


    public List<User> getUsersBySubjectId(Long subjectId) {
        List<UserSubject> usersSubject = new ArrayList<>();
        List<User> users = new ArrayList<>();
        userSubjectRepository.findAllBySubjectId(subjectId).iterator().forEachRemaining(usersSubject::add);
        for (UserSubject userSubject : usersSubject) {
            users.add(this.getUserById(userSubject.getUser().getId()));
        }
        return users;
    }

    public UserDto changeUserPassword(String password, String email) {
        User user = userRepository.findUserByEmail(email).get(0);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        return userMapper.mapUserToUserDto(user);
    }


}

