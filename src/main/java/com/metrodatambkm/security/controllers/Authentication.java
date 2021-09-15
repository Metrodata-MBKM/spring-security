package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.request.LoginRequest;
import com.metrodatambkm.security.dtos.request.RegisterRequest;
import com.metrodatambkm.security.dtos.response.ConfirmationResponse;
import com.metrodatambkm.security.dtos.response.LoginResponse;
import com.metrodatambkm.security.dtos.response.RegisterResponse;
import com.metrodatambkm.security.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class Authentication {

    AuthenticationService authenticationService;
    ApplicationEventPublisher eventPublisher;

    @Autowired
    public Authentication(AuthenticationService authenticationService, ApplicationEventPublisher eventPublisher) {
        this.authenticationService = authenticationService;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request){
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request){
        return authenticationService.login(request);
    }

    @GetMapping("/confirm/{token}")
    public ConfirmationResponse confirmRegistration(@PathVariable String token){
        return authenticationService.confirmRegistration(token);
    }
}
