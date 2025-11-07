package com.example.UserProject.Services;


import com.example.UserProject.Model.UserEntity;
import com.example.UserProject.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("Username not found !"));

        return new User(user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("USER_ROLE")));
    }


}
