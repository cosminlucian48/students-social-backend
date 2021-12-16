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
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<UserDetailsDto> getAllUserDetails(){
        List<UserDetailsDto> userDetailsDtos = new ArrayList<>();
        List<UserDetails> userDetails= new ArrayList<>();

        userDetailsRepository.findAll().iterator().forEachRemaining(userDetails::add);
        return userDetails.stream().map(elem -> userMapperDetails.mapUserDetailsToUserDetailsDto(elem)).collect(Collectors.toList());

    }

    public UserDetailsDto saveUser(UserDetailsDto userDetailsDto) {
        UserDetails userDetails = userMapperDetails.mapUserDetailsDtoToUserDetails(userDetailsDto);
        UserDetails savedUserDetails = userDetailsRepository.save(userDetails);
        System.out.print(userDetailsDto);
        return userMapperDetails.mapUserDetailsToUserDetailsDto(savedUserDetails);
    }


    public User getUserById(Long id) {

        Optional <User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }

    public UserDetails getUserDetailsById(Long id) {

        Optional <UserDetails> optionalUserDetails = userDetailsRepository.findById(id);
        return optionalUserDetails.orElse(null);
    }

    public UserDetails updateUser(Long id, UserDetails userDetails){

        Optional <UserDetails> optionalUserDetails = userDetailsRepository.findById(id);
        if(optionalUserDetails.isPresent()) {
            userDetails.setId(id);
            return userDetailsRepository.save(userDetails);
        }else { throw new NoSuchElementException(String.valueOf(userDetails));
        }
    }

}



