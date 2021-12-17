package com.example.studentssocial.mapper;

import com.example.studentssocial.dto.UserDto;
import com.example.studentssocial.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUniversity(userDto.getUniversity());
        user.setRegistrationDate(userDto.getRegistrationDate());

        return user;
    }

    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUniversity(user.getUniversity());
        userDto.setRegistrationDate(user.getRegistrationDate());
        userDto.setId(user.getId());

        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
