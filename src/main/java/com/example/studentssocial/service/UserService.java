package com.example.studentssocial.service;

import com.example.studentssocial.dto.UserDetailsDto;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserDetails;
import com.example.studentssocial.mapper.UserDetailsMapper;
import com.example.studentssocial.repository.UserDetailsRepository;
import com.example.studentssocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@RequiredArgsConstructor //constructor cu parametrii final
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDetailsRepository userDetailsRepository;

    private final UserDetailsMapper userMapperDetails;

    @Autowired
    public UserService(UserRepository userRepository, UserDetailsRepository userDetailsRepository, UserDetailsMapper userMapperDetails) {
        this.userRepository = userRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.userMapperDetails = userMapperDetails;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }


//    public List<UserDetails> getAllUsersDetails(){
//        List<UserDetails> usersDetails = new ArrayList<>();
//        userDetailsRepository.findAll().iterator().forEachRemaining(usersDetails::add);
//        return usersDetails;
//    }

    public UserDetailsDto saveUser(UserDetailsDto userDetailsDto) {
        UserDetails userDetails = userMapperDetails.mapUserDetailsDtoToUserDetails(userDetailsDto);
        UserDetails savedUserDetails = userDetailsRepository.save(userDetails);
        return userMapperDetails.mapUserDetailsToUserDetailsDto(savedUserDetails);
    }
}
