/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author hp
 */
@Component
public class EmailService {
    
    //menerapkan kelas JavaMailSender
    @Autowired
    private JavaMailSender emailSender;
    
    //membuat method untuk kirim email dengan MimeMessageHelper
    public void sendMessage(String to, String subject, String text){
        try{

            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");
            message.setFrom("agung.prabs08@gmail.com");
            helper.setTo(to); // tujuan
            helper.setSubject(subject); // subject
            helper.setText(text, true); // body message
            emailSender.send(message);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}