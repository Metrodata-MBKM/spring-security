/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.dtos.ConfirmationResponseDTO;
import com.mbkm.hr.dtos.LoginRequestDTO;
import com.mbkm.hr.dtos.LoginResponseDTO;
import com.mbkm.hr.dtos.RegisterRequestDTO;
import com.mbkm.hr.dtos.RegisterResponseDTO;
import com.mbkm.hr.models.Privilege;
import com.mbkm.hr.models.Role;
import com.mbkm.hr.models.User;
import com.mbkm.hr.models.VerificationToken;
import com.mbkm.hr.repositories.AppUserRepository;
import com.mbkm.hr.repositories.RoleRepository;
import com.mbkm.hr.repositories.VerificationTokenRepository;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    
    public LoginResponseDTO login(LoginRequestDTO request){
        User user = appUserRepository.findByUsername(request.getUsername());
        
        System.out.println("result = "+user);
        if(user == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found!");
        }else if(!encoder.matches(request.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password!");
        }else if(user.isEnabled() == false){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User has not verified");
        }else{
            Set<Role> userRole = user.getRoles();
            Set<String> autho = new HashSet<>();
            for (Role role : userRole) {
                autho.add(role.getName());
                for (Privilege privilege : role.getPrivileges()) {
                    autho.add(privilege.getName());
                }
            }
            
            return new LoginResponseDTO(createLoginToken(request.getUsername(), request.getPassword()), autho);
        }
        
    }

    public String createLoginToken(String identity, String password){
        String auth = identity + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(
                auth.getBytes(Charset.forName("US-ASCII"))
        );

        String authHeader = new String(encodedAuth);
        return authHeader;
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
