/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.listeners;

import org.thymeleaf.context.Context;
import com.metrodatambkm.security.events.OnRegistrationCompleteEvent;
import com.metrodatambkm.security.models.User;
import com.metrodatambkm.security.repositories.UserRepository;
import com.metrodatambkm.security.services.AuthenticationService;
import com.metrodatambkm.security.services.EmailService;
import java.util.UUID;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.beans.factory.annotation.Autowired;



/**
 *
 * @author gabri
 */
@Component
public class RegisterListener  implements ApplicationListener<OnRegistrationCompleteEvent>{

    private UserRepository repository;
    private AuthenticationService service;
    private EmailService emailService;
    private TemplateEngine templateEngine;
    
    @Autowired
    public RegisterListener(UserRepository repository, AuthenticationService service, EmailService emailService, TemplateEngine templateEngine) {
        this.repository = repository;
        this.service = service;
        this.emailService = emailService;
        this.templateEngine = templateEngine;
    }
    
    
    
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
          this.confirmRegistration(onRegistrationCompleteEvent);
    }
    
    private void confirmRegistration(OnRegistrationCompleteEvent event){
        String username = event.getRegisterResponseDTO().getUsername();
        String email = event.getRegisterResponseDTO().getEmail();
        User user = repository.findByUsernameOrEmail(username, email);

        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation Account";
        String confirmationUrl  = "http://localhost:8080/auth/confirm/" + token;

        Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("email", user.getEmail());
        ctx.setVariable("name", user.getUsername());
        ctx.setVariable("url", confirmationUrl);

        String htmlContent = this.templateEngine.process("confirmation-email", ctx);

        emailService.sendMessage(recipientAddress,subject,htmlContent);

    }
    
}
