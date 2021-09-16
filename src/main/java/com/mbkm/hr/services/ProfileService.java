/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.dtos.response.ProfileResponseDTO;
import com.mbkm.hr.models.credentials.User;
import com.mbkm.hr.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class ProfileService {
    private AppUserRepository userRepository;

    @Autowired
    public ProfileService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProfileResponseDTO profile(String name) {
        User user = userRepository.findByUsernameOrEmployee_Email(name, name);
        ProfileResponseDTO profile = ProfileResponseDTO 
                .builder()
                .username(user.getUsername())
                .email(user.getEmployee().getEmail())
                .firstName(user.getEmployee().getFirstName())
                .lastName(user.getEmployee().getLastName())
                .department(user.getEmployee().getDepartment().getName())
                .phoneNumber(user.getEmployee().getPhoneNumber())
                .job(user.getEmployee().getJob().getTitle())
                .manager(user.getEmployee().getManagerId() == null ? "" : user.getEmployee().getManagerId().getLastName()) 
                .build();

        return profile;
    }
}
