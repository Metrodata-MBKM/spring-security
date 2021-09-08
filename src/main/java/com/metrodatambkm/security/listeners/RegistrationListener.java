package com.metrodatambkm.security.listeners;

import com.metrodatambkm.security.components.EmailService;
import com.metrodatambkm.security.entities.credentials.User;
import com.metrodatambkm.security.events.OnRegistrationCompleteEvent;
import com.metrodatambkm.security.repositories.UserRepository;
import com.metrodatambkm.security.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationService service;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MessageSource messages;

    @Autowired
    private JavaMailSender mailSender;

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
        String message = "Registration successfully \n" +
                "Please follow the url " +
                "http://localhost:8084" + confirmationUrl;

        emailService.sendMessage(recipientAddress,subject,message);

    }
}
