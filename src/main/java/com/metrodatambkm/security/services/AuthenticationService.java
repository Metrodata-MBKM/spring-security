package com.metrodatambkm.security.services;

import com.metrodatambkm.security.configs.PasswordEncoderConfig;
import com.metrodatambkm.security.dtos.ConfirmationResponse;
import com.metrodatambkm.security.dtos.RegisterRequest;
import com.metrodatambkm.security.dtos.RegisterResponse;
import com.metrodatambkm.security.entities.credentials.User;
import com.metrodatambkm.security.entities.credentials.VerificationToken;
import com.metrodatambkm.security.entities.permission.Role;
import com.metrodatambkm.security.repositories.RoleRepository;
import com.metrodatambkm.security.repositories.UserRepository;
import com.metrodatambkm.security.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

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
}
