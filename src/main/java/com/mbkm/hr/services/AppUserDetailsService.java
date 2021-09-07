/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.AppUserDetails;
import com.mbkm.hr.models.User;
import com.mbkm.hr.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author loisceka
 */
@Service
public class AppUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String params) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(params, params);
        return new AppUserDetails(user);
    }

}
