/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.dto.MailRequest;
import com.mbkm.hr.dto.MailResponse;
import com.mbkm.hr.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rebel
 */
@RestController
public class SendingEmail {
    @Autowired
    private EmailService service;

    @PostMapping("/sendingEmail")
    public MailResponse sendEmail(@RequestBody MailRequest request) {

        return service.sendEmail(request);

    }
}
