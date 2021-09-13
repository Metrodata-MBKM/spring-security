/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.listeners;

import com.mbkm.hr.events.OnRegistrationCompleteEvent;
import com.mbkm.hr.models.credentials.User;
import com.mbkm.hr.repositories.AppUserRepository;
import com.mbkm.hr.services.UserManagementService;
import com.mbkm.hr.services.EmailService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.context.i18n.LocaleContextHolder;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

/**
 *
 * @author hp
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>{
    private AppUserRepository repository;
    private UserManagementService service;
    private EmailService emailService;
    private TemplateEngine templateEngine;

    @Autowired
    public RegistrationListener(AppUserRepository repository, UserManagementService service, EmailService emailService, TemplateEngine templateEngine) {
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
        //mengambil nilai username dan email ketika berhasil register
        String username = event.getRegisterResponseDTO().getUsername();
        String email = event.getRegisterResponseDTO().getEmail();
        User user = repository.findByUsernameOrEmployee_Email(username, email);
        
        //membuat token dengan randomUUID
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);
        
        //menginisialisasi email penerima, subjek, dan isi email
        String recipientAddress = user.getEmployee().getEmail();
        String subject = "Verification Account";
        String Url  = "http://localhost:8088/auth/confirm/" + token;

        Context ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("email", user.getEmployee().getEmail());
        ctx.setVariable("username", user.getUsername());
        ctx.setVariable("url", Url);
        String htmlContent = this.templateEngine.process("email-verification", ctx);

        emailService.sendMessage(recipientAddress,subject,htmlContent);

    }
}
