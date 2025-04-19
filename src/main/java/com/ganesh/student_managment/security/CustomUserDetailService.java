package com.ganesh.student_managment.security;

import com.ganesh.student_managment.entity.Users;
import com.ganesh.student_managment.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {


    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailService.class);

    @Autowired
    private UsersRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepo.findByEmailOrMobile(username, username);
        if(null == user) {
            throw new UsernameNotFoundException(String.format("User % not found", username));
        }
        return user;
    }
}


