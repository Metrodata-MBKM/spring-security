/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dto.ConfirmationResponseDTO;
import com.metrodatambkm.security.dto.LoginRequestDTO;
import com.metrodatambkm.security.dto.LoginResponseDTO;
import com.metrodatambkm.security.dto.RegisterRequestDTO;
import com.metrodatambkm.security.dto.RegisterResponseDTO;
import com.metrodatambkm.security.events.OnRegistrationCompleteEvent;
import com.metrodatambkm.security.exceptions.AlreadyExistsException;
import com.metrodatambkm.security.exceptions.ResourceNotFoundException;
import com.metrodatambkm.security.exceptions.UnauthorizedException;
import com.metrodatambkm.security.models.Privilege;
import com.metrodatambkm.security.models.Role;
import com.metrodatambkm.security.models.TokenVerification;
import com.metrodatambkm.security.models.User;
import com.metrodatambkm.security.repositories.RoleRepository;
import com.metrodatambkm.security.repositories.UserRepository;
import com.metrodatambkm.security.repositories.VerificationTokenRepository;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author gabri
 */
@Service
public class AuthenticationService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    VerificationTokenRepository tokenRepository;
    PasswordEncoder encoder;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, VerificationTokenRepository tokenRepository, PasswordEncoder encoder, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.encoder = encoder;
        this.eventPublisher = eventPublisher;
    }

    ApplicationEventPublisher eventPublisher;

    public RegisterResponseDTO register(RegisterRequestDTO request){

        if(userRepository.findByUsername(request.getUsername()) != null){
            throw new AlreadyExistsException("Username already exists");
        }

        if(userRepository.findByEmail(request.getEmail()) != null){
            throw new AlreadyExistsException("Email already exists");
        }

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("OPERATOR"));

        User user = new User(
                null,
                request.getUsername(),
                encoder.encode(request.getPassword()),
                request.getEmail(),
                false,
                roles);

        RegisterResponseDTO response = new RegisterResponseDTO().generate(userRepository.save(user));

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(response));

        return response;
    }

    public LoginResponseDTO login(LoginRequestDTO request){
//        User user = userRepository.findByUsernameOrEmail(request.getUsername(), request.getUsername());
      User user = userRepository.findByUsernameOrEmployee_Email(request.getUsername(), request.getUsername());

        if(user == null) {
            throw new ResourceNotFoundException("User not found!");
        }

        if(!user.isEnabled()){
            throw new UnauthorizedException("Your account has not been activated");
        }

        if(!encoder.matches(request.getPassword(), user.getPassword())){
            throw new UnauthorizedException("Wrong credentials!");
        }
        else{
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
        TokenVerification myToken = new TokenVerification(token, user);
        tokenRepository.save(myToken);
    }

    public ConfirmationResponseDTO confirmRegistration(String token){
        TokenVerification verificationToken = tokenRepository.findByToken(token);
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if(token == null){
            return new ConfirmationResponseDTO(false, "Token invalid");
        }

        if(verificationToken.getExpireDate().getTime() - cal.getTime().getTime() <= 0){
            return new ConfirmationResponseDTO(false, "Token Expired");
        }

        user.setEnabled(true);
        userRepository.save(user);
        return new ConfirmationResponseDTO(true, "Account Activated");
    }
}
