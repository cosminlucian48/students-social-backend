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
        user.setFaculty(userDto.getFaculty());
        user.setRegistrationDate(userDto.getRegistrationDate());
        user.setAuthorities(userDto.getAuthorities());
        user.setProfileImage(userDto.getProfileImage());
        user.setTagEmail(userDto.getTagEmail());
        user.setPostEmail(userDto.getPostEmail());
        user.setCommentEmail(userDto.getCommentEmail());
        return user;
    }

    public UserDto mapUserToUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUniversity(user.getUniversity());
        userDto.setFaculty(user.getFaculty());
        userDto.setRegistrationDate(user.getRegistrationDate());
        userDto.setId(user.getId());
        userDto.setAuthorities(user.getAuthorities());
        userDto.setProfileImage(user.getProfileImage());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setTagEmail(user.getTagEmail());
        userDto.setPostEmail(user.getPostEmail());
        userDto.setCommentEmail(user.getCommentEmail());
        return userDto;
    }
}
