package com.example.studentssocial.service;

import com.example.studentssocial.entity.User;
import com.example.studentssocial.models.UserPrincipal;
import com.example.studentssocial.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        List<User> user = userRepository.findUserByEmail(email);
//        String encodedUserPassword = bCryptPasswordEncoder.encode();
        if(user.size()==1){
            return new UserPrincipal(user.get(0));
        }
        throw new RuntimeException("nu ar trebui sa se intampla asta");

//        return new User("admin", encodedUserPassword, new ArrayList<>());
    }
}
