/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.dtos.ConfirmationResponseDTO;
import com.mbkm.hr.dtos.RegisterRequestDTO;
import com.mbkm.hr.dtos.RegisterResponseDTO;
import com.mbkm.hr.events.OnRegistrationCompleteEvent;
import com.mbkm.hr.services.UserManagementService;
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
 * @author hp
 */
@RestController
@RequestMapping("/auth")
public class UserRegisterController {
    
    UserManagementService authenticationService;
    ApplicationEventPublisher eventPublisher;
    
    @Autowired
    public UserRegisterController(UserManagementService authenticationService, ApplicationEventPublisher eventPublisher) {
        this.authenticationService = authenticationService;
        this.eventPublisher = eventPublisher;
    }
    
    @PostMapping("/register")
    public RegisterResponseDTO register(@RequestBody RegisterRequestDTO request, HttpServletRequest servletRequest){
        try{
            RegisterResponseDTO response = authenticationService.register(request);
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
    public ConfirmationResponseDTO confirmRegistration(@PathVariable String token){
        return authenticationService.confirmRegistration(token);
    }
}
