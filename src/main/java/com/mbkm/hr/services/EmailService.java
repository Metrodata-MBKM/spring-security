/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

/**
 *
 * @author Kevitha
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMessage;
import org.springframework.stereotype.Component;


@Component
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendMessage(String to, String subject, String text){
        try{

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");
            message.setFrom("kvtpraisyf@gmail.com");
            helper.setTo(to); 
            helper.setSubject(subject); 
            helper.setText(text, true); 
            emailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}