/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.listeners;

import com.mbkm.hr.events.OnRegistrationCompleteEvent;
import com.mbkm.hr.models.User;
import com.mbkm.hr.repositories.UserRepository;
import com.mbkm.hr.services.AuthenticationService;
import com.mbkm.hr.services.EmailService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author loisceka
 */
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
        // TAKING USERNAME AND PASSWORD AFTER REGISTER
        String username = event.getRegisterResponse().getUsername();
        String email = event.getRegisterResponse().getEmail();
        User user = repository.findByUsernameOrEmployee_Email(username, email);
        
        // CREATING TOKEN FOR VERIFICATION USING RANDOMUUID
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        // MAIL SERVICE 
        String recipientAddress = user.getEmployee().getEmail();
        String subject = "Verification Email ";
        String confirmationUrl  = "http://localhost:8080/auth/confirm/" + token;
        String message = "<h1>Thankyou for registering, " + user.getUsername() +
                "!</h1> <br> Please verify your email to secure your account, <br>" +
                "Click the following link to verify your email -> " +
                confirmationUrl;

        emailService.sendMessage(recipientAddress,subject,message);

    }
}
