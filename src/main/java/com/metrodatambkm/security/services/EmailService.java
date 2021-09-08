package com.metrodatambkm.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendMessage(String to, String subject, String text){
        try{

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");
            message.setFrom("devklvn@gmail.com");
            helper.setTo(to); // tujuan
            helper.setSubject(subject); // subject
            helper.setText(text, true); // body message
            emailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
