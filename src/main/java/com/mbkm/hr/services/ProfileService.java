/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.dtos.ProfileDTO;
import com.mbkm.hr.models.User;
import com.mbkm.hr.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Kevitha
 */
@Service
public class ProfileService {
    private UserRepository userRepository;

    @Autowired
    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public ProfileDTO profile(String username){
        User user = userRepository.findByUsernameOrEmployee_Email(username, username);
        ProfileDTO profile = ProfileDTO 
                .builder()
                .username(user.getUsername())
                .email(user.getEmployee().getEmail())
                .firstName(user.getEmployee().getFirstName())
                .lastName(user.getEmployee().getLastName())
                .department(user.getEmployee().getDepartment().getName())
                .phoneNumber(user.getEmployee().getPhoneNumber())
                .job(user.getEmployee().getJob().getTitle())
                .manager(user.getEmployee().getManager() == null ? "" : user.getEmployee().getManager().getLastName()) 
                .build();
        
        return profile;
    }
}
