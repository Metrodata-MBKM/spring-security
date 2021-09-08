package com.metrodatambkm.security.listeners;

import com.metrodatambkm.security.services.EmailService;
import com.metrodatambkm.security.entities.credentials.User;
import com.metrodatambkm.security.events.OnRegistrationCompleteEvent;
import com.metrodatambkm.security.repositories.UserRepository;
import com.metrodatambkm.security.services.AuthenticationService;
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
        System.out.println("===================== RUN THE LISTENER ==================");
        String username = event.getRegisterResponse().getUsername();
        String email = event.getRegisterResponse().getEmail();
        User user = repository.findByUsernameOrEmail(username, email);

        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl  = event.getAppUrl() + "/auth/confirm/" + token;
        String message = "<h1>Haiii " + user.getUsername() + " ..</h1> <br> Registration successfully \n" +
                "Please follow the url " +
                "http://localhost:8084" + confirmationUrl;

        emailService.sendMessage(recipientAddress,subject,message);

    }
}
