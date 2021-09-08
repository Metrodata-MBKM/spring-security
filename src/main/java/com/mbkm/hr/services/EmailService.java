/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.dto.MailRequest;
import com.mbkm.hr.dto.MailResponse;
import java.nio.charset.StandardCharsets;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

/**
 *
 * @author rebel
 */
@Component
public class EmailService {
    
    @Autowired
    private JavaMailSender sender;

    public MailResponse sendEmail(MailRequest request) {
        MailResponse response = new MailResponse();
        MimeMessage message = sender.createMimeMessage();
        try {

            // set mediaType
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());


            helper.setTo(request.getTo());
            helper.setText("Test email", true);
            helper.setSubject(request.getSubject());
            helper.setFrom(request.getFrom());
            sender.send(message);

            response.setMessage("mail send to : " + request.getTo());
            response.setStatus(Boolean.TRUE);

        } catch (MessagingException e) {
            response.setMessage("Mail Sending failure : "+e.getMessage());
            response.setStatus(Boolean.FALSE);
        }

        return response;
    }
}
