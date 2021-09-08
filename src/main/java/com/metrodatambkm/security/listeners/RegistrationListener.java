package com.metrodatambkm.security.listeners;

import com.metrodatambkm.security.services.EmailService;
import com.metrodatambkm.security.entities.credentials.User;
import com.metrodatambkm.security.events.OnRegistrationCompleteEvent;
import com.metrodatambkm.security.repositories.UserRepository;
import com.metrodatambkm.security.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.UUID;
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private UserRepository repository;
    private AuthenticationService service;
    private EmailService emailService;
    private TemplateEngine templateEngine;

    @Autowired
    public RegistrationListener(UserRepository repository, AuthenticationService service, EmailService emailService, SpringTemplateEngine templateEngine) {
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
        System.out.println("===================== RUN THE LISTENER ==================");
        String username = event.getRegisterResponse().getUsername();
        String email = event.getRegisterResponse().getEmail();
        User user = repository.findByUsernameOrEmail(username, email);

        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);


        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";

        String confirmationUrl  = "http://localhost:8084/auth/confirm/" + token;

        Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("email", user.getEmail());
        ctx.setVariable("name", user.getUsername());
        ctx.setVariable("url", confirmationUrl);

        String htmlContent = this.templateEngine.process("confirmation-email", ctx);

        emailService.sendMessage(recipientAddress,subject,htmlContent);

    }
}
