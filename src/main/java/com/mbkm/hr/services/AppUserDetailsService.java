/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.AppUserDetails;
import com.mbkm.hr.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.mbkm.hr.repositories.UserRepository;

/**
 *
 * @author WahyuKu
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository appUserRepository;

    public AppUserDetailsService(UserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String parameter)throws UsernameNotFoundException{
        System.out.println(appUserRepository.findByUsernameOrEmail(parameter, parameter));
        User user = appUserRepository.findByUsernameOrEmail(parameter, parameter);
        
        return new AppUserDetails(user);
    }
    
}
