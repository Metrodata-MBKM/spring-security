package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.ConfirmationResponse;
import com.metrodatambkm.security.dtos.RegisterRequest;
import com.metrodatambkm.security.dtos.RegisterResponse;
import com.metrodatambkm.security.events.OnRegistrationCompleteEvent;
import com.metrodatambkm.security.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public RegisterResponse register(@RequestBody RegisterRequest request, HttpServletRequest servletRequest){
        try{
            RegisterResponse response = authenticationService.register(request);
            String appUrl = servletRequest.getContextPath();

            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(
                    response,
                    servletRequest.getLocale(),
                    appUrl
            ));

            return response;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @GetMapping("/confirm/{token}")
    public ConfirmationResponse confirmRegistration(@PathVariable String token){
        return authenticationService.confirmRegistration(token);
    }
}
