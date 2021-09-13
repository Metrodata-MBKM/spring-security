/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.listeners;

/**
 *
 * @author Kevitha
 */

import com.mbkm.hr.events.OnRegistrationCompleteEvent;
import com.mbkm.hr.models.User;
import com.mbkm.hr.repositories.UserRepository;
import com.mbkm.hr.services.AuthenticationService;
import com.mbkm.hr.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private UserRepository repository;
    private AuthenticationService service;
    private EmailService emailService;

    @Autowired
    public RegistrationListener(UserRepository repository, AuthenticationService service, EmailService emailService) {
        this.repository = repository;
        this.service = service;
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){
        //mengambil nilai username dan email ketika berhasil register
        String username = event.getRegisterResponse().getUsername();
        String email = event.getRegisterResponse().getEmail();
        User user = repository.findByUsernameOrEmployee_Email(username, email);
        
        //membuat token dengan randomUUID
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        //menginisialisasi email penerima, subjek, dan isi email
        String recipientAddress = user.getEmployee().getEmail();
        String subject = "[MBKM_HR] Verification Email ";
        String confirmationUrl  = "http://localhost:8080/auth/confirm/" + token;
        String message = "<h1>Thankyou for registering, " + user.getUsername() +
                "!</h1> <br> You are registered successfully, <br>" +
                "Click the link to verify your email -> " +
                confirmationUrl;

        emailService.sendMessage(recipientAddress,subject,message);

    }
}