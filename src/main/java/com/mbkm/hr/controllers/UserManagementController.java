/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.dtos.response.ConfirmationResponseDTO;
import com.mbkm.hr.dtos.request.LoginRequestDTO;
import com.mbkm.hr.dtos.response.LoginResponseDTO;
import com.mbkm.hr.dtos.request.RegisterRequestDTO;
import com.mbkm.hr.dtos.response.RegisterResponseDTO;
import com.mbkm.hr.events.OnRegistrationCompleteEvent;
import com.mbkm.hr.repositories.AppUserRepository;
import com.mbkm.hr.services.UserManagementService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/auth")
public class UserManagementController {
    
    UserManagementService authenticationService;
    ApplicationEventPublisher eventPublisher;
    AppUserRepository appUserRepository;
    
    @Autowired
    public UserManagementController(UserManagementService authenticationService, ApplicationEventPublisher eventPublisher, AppUserRepository appUserRepository) {
        this.authenticationService = authenticationService;
        this.eventPublisher = eventPublisher;
        this.appUserRepository = appUserRepository;

    }
    
    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody RegisterRequestDTO request){
        return authenticationService.register(request);
    }
    
    @GetMapping("/confirm/{token}")
    public ConfirmationResponseDTO confirmRegistration(@PathVariable String token){
        return authenticationService.confirmRegistration(token);
    }
    
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO request){
        return authenticationService.login(request);
    }
}
