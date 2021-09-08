/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.listeners;

import com.mbkm.hr.events.OnRegistrationCompleteEvent;
import com.mbkm.hr.models.User;
import com.mbkm.hr.repositories.AppUserRepository;
import com.mbkm.hr.services.UserManagementService;
import com.mbkm.hr.services.EmailService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author hp
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>{
    private AppUserRepository repository;
    private UserManagementService service;
    private EmailService emailService;

    @Autowired
    public RegistrationListener(AppUserRepository repository, UserManagementService service, EmailService emailService) {
        this.repository = repository;
        this.service = service;
        this.emailService = emailService;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event){
        System.out.println("===================== RUN THE LISTENER ==================");
        String username = event.getRegisterResponseDTO().getUsername();
        String email = event.getRegisterResponseDTO().getEmail();
        User user = repository.findByUsernameOrEmail(username, email);

        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl  = event.getAppUrl() + "/auth/confirm/" + token;
        String message = "<h1>Haiii " + user.getUsername() + " ..</h1> <br> Registration successfully \n" +
                "Please follow the url " +
                "http://localhost:8080" + confirmationUrl;

        emailService.sendMessage(recipientAddress,subject,message);

    }
}
