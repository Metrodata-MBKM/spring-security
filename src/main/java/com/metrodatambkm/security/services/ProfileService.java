/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dto.ProfileDTO;
import com.metrodatambkm.security.models.User;
import com.metrodatambkm.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gabri
 */
@Service
public class ProfileService {
    private UserRepository userRepository;

    @Autowired
    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public  ProfileDTO profile(String username){
        User user=userRepository.findByUsernameOrEmployee_Email(username, username).get();
        
        return ProfileDTO
                .builder()
                .username(user.getUsername())
                .email(user.getEmploeyee().getEmail())
                .firsName(user.getEmploeyee().getFirstName())
                .lastName(user.getEmploeyee().getLastName())
                .build();
    }
    
    
}
