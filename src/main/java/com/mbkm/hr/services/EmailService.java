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
import com.mbkm.hr.dtos.MailRequest;
import com.mbkm.hr.dtos.MailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender sender;


    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.sender = mailSender;
    }

    public MailResponse sendEmail(MailRequest request) {
        MailResponse response = new MailResponse();
        MimeMessage message = sender.createMimeMessage();
        try {

            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setTo(request.getTo());
            helper.setText("<h1>Halo<h1>", true);
            helper.setSubject(request.getSubject());
            helper.setFrom(request.getFrom());
            sender.send(message);

            response.setMessage("Mail Successfully Sent to : " + request.getTo());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException e) {
            response.setMessage("Mail Failed Sent to: "+e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }

}