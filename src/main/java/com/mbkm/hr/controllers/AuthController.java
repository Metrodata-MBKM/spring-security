/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.dto.AuthRequestDTO;
import com.mbkm.hr.dto.AuthResponseDTO;
import com.mbkm.hr.dto.ConfirmationResponse;
import com.mbkm.hr.dto.RegisterRequest;
import com.mbkm.hr.dto.RegisterResponse;
import com.mbkm.hr.events.OnRegistrationCompleteEvent;
import com.mbkm.hr.services.AuthenticationService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author loisceka
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    AuthenticationService authenticationService;
    ApplicationEventPublisher eventPublisher;

     @Autowired
    public AuthController(AuthenticationService authenticationService, ApplicationEventPublisher eventPublisher) {
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
    
    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO request){
        return authenticationService.login(request);
    }
}
