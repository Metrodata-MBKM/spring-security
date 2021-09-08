package com.metrodatambkm.security.components;

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
            MimeMessageHelper helper = new MimeMessageHelper(message    , "utf-8");
            helper.setFrom("devklvn@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            emailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
