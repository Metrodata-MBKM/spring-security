/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.credentials.AppUserDetails;
import com.mbkm.hr.models.credentials.User;
import com.mbkm.hr.repositories.AppUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author WahyuKu
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
    
    private AppUserRepository appUserRepository;
    private EmailService emailService;
    
    @Autowired
    public AppUserDetailsService(AppUserRepository userRepository, EmailService emailService) {
        this.appUserRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String params)throws UsernameNotFoundException{
        System.out.println(params);
        User user = appUserRepository.findByUsername(params);
        System.out.println(user);
        return new AppUserDetails(user);
    }

    public AppUserDetailsService() {
    }
}
