/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.DTO.ConfirmationResponse;
import com.mbkm.hr.DTO.LoginRequestDTO;
import com.mbkm.hr.DTO.LoginResponseDTO;
import com.mbkm.hr.DTO.RegisterRequest;
import com.mbkm.hr.DTO.RegisterResponse;
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
 * @author Dony Tri P
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest request){
        return authenticationService.register(request);
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request){
        return authenticationService.login(request);
    }

    @GetMapping("/confirm/{token}")
    public ConfirmationResponse confirmRegistration(@PathVariable String token){
        return authenticationService.confirmRegistration(token);
    }
}