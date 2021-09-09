package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dtos.*;
import com.metrodatambkm.security.entities.credentials.User;
import com.metrodatambkm.security.entities.credentials.VerificationToken;
import com.metrodatambkm.security.entities.permission.Role;
import com.metrodatambkm.security.events.OnRegistrationCompleteEvent;
import com.metrodatambkm.security.exceptions.ResourceAlreadyExists;
import com.metrodatambkm.security.exceptions.ResourceNotFoundException;
import com.metrodatambkm.security.exceptions.UnauthorizedException;
import com.metrodatambkm.security.repositories.RoleRepository;
import com.metrodatambkm.security.repositories.UserRepository;
import com.metrodatambkm.security.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.*;

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

    public RegisterResponse register(RegisterRequest request){

        if(userRepository.findByUsername(request.getUsername()) != null){
            throw new ResourceAlreadyExists("Username already exists");
        }

        if(userRepository.findByEmail(request.getEmail()) != null){
            throw new ResourceAlreadyExists("Email already exists");
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

        RegisterResponse response = new RegisterResponse().generate(userRepository.save(user));

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(response));

        return response;
    }

    public LoginResponse login(LoginRequest request){
        User user = userRepository.findByUsernameOrEmail(request.getUsername(), request.getUsername());

        if(user == null) {
            throw new ResourceNotFoundException("User not found!");
        }

        if(!user.isEnabled()){
            throw new UnauthorizedException("Your account has not been activated");
        }

        if(!encoder.matches(request.getPassword(), user.getPassword())){
            throw new UnauthorizedException("Wrong credentials!");
        }

        return new LoginResponse(createLoginToken(request.getUsername(), request.getPassword()), user.getRoles());
    }

    public String createLoginToken(String identity, String password){
        
        String auth = identity + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(
                auth.getBytes(Charset.forName("US-ASCII"))
        );

        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
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
}
