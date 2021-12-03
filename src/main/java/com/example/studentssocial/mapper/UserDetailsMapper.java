package com.example.studentssocial.mapper;

import com.example.studentssocial.dto.UserDetailsDto;
import com.example.studentssocial.entity.User;
import com.example.studentssocial.entity.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsMapper {
    public UserDetails mapUserDetailsDtoToUserDetails(UserDetailsDto userDto){
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        UserDetails userDetails = new UserDetails();
        userDetails.setFirstName(userDto.getFirstName());
        userDetails.setLastName(userDto.getLastName());
        userDetails.setUniversity(userDto.getUniversity());
        userDetails.setRegistrationDate(userDto.getRegistrationDate());

        userDetails.setUser(user);
        return userDetails;
    }

    public UserDetailsDto mapUserDetailsToUserDetailsDto(UserDetails userDetails){
        UserDetailsDto userDetailsDto =new UserDetailsDto();

        return userDetailsDto;
    }
}
