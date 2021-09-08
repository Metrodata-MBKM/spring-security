/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.dtos.ConfirmationResponseDTO;
import com.mbkm.hr.dtos.RegisterRequestDTO;
import com.mbkm.hr.dtos.RegisterResponseDTO;
import com.mbkm.hr.models.Role;
import com.mbkm.hr.models.User;
import com.mbkm.hr.models.VerificationToken;
import com.mbkm.hr.repositories.AppUserRepository;
import com.mbkm.hr.repositories.RoleRepository;
import com.mbkm.hr.repositories.VerificationTokenRepository;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class UserManagementService {
    AppUserRepository appUserRepository;
    RoleRepository roleRepository;
    VerificationTokenRepository tokenRepository;
    PasswordEncoder encoder;
    
    @Autowired
    public UserManagementService(AppUserRepository appUserRepository, RoleRepository roleRepository, VerificationTokenRepository tokenRepository, PasswordEncoder encoder) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.encoder = encoder;
    }
    
    public RegisterResponseDTO register(RegisterRequestDTO request){

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("OPERATOR"));

        User user = new User(
                null,
                request.getUsername(),
                encoder.encode(request.getPassword()),
                request.getEmail(),
                false,
                roles);

        return new RegisterResponseDTO().generate(appUserRepository.save(user));
    }
    
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    public ConfirmationResponseDTO confirmRegistration(String token){
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if(token == null){
            return new ConfirmationResponseDTO(false, "Token invalid");
        }

        if(verificationToken.getExpireDate().getTime() - cal.getTime().getTime() <= 0){
            return new ConfirmationResponseDTO(false, "Token Expired");
        }

        user.setEnabled(true);
        appUserRepository.save(user);
        return new ConfirmationResponseDTO(true, "Account Activated");
    }
}
