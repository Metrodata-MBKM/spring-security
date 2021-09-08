/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.DTO.ConfirmationResponse;
import com.mbkm.hr.DTO.LoginRequestDTO;
import com.mbkm.hr.DTO.LoginResponseDTO;
import com.mbkm.hr.DTO.RegisterRequest;
import com.mbkm.hr.DTO.RegisterResponse;
import com.mbkm.hr.models.Role;
import com.mbkm.hr.models.User;
import com.mbkm.hr.models.VerificationToken;
import com.mbkm.hr.repositories.RoleRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.mbkm.hr.repositories.UserRepository;
import com.mbkm.hr.repositories.VerificationTokenRepository;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Calendar;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Dony Tri P
 */
@Service
public class AuthenticationService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    VerificationTokenRepository tokenRepository;
    PasswordEncoder encoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, VerificationTokenRepository tokenRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.encoder = encoder;
    }

    public RegisterResponse register(RegisterRequest request){

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("OPERATOR"));

        User user = new User(
                null,
                request.getUsername(),
                encoder.encode(request.getPassword()),
                request.getEmail(),
                false,
                roles);

        return new RegisterResponse().generate(userRepository.save(user));
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    public ConfirmationResponse confirmRegistration(String token){
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if(token == null){
            return new ConfirmationResponse(false, "Token invalid");
        }

        if(verificationToken.getExpireDate().getTime() - cal.getTime().getTime() <= 0){
            return new ConfirmationResponse(false, "Token Expired");
        }

        user.setEnabled(true);
        userRepository.save(user);
        return new ConfirmationResponse(true, "Account Activated");
    }
    
    public String CreateLoginToken(String identity, String password){
        String auth = identity + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(
                auth.getBytes(Charset.forName("US-ASCII"))
        );

        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
    
    public LoginResponseDTO login(LoginRequestDTO request){
        User user = userRepository.findByUsernameOrEmail(request.getUsername(), request.getUsername());

        System.out.println("result = "+user);
        if(!encoder.matches(request.getPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password!");
        }

        if(user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
        return new LoginResponseDTO(CreateLoginToken(request.getUsername(), request.getPassword()), user.getRoles());
    }
}
