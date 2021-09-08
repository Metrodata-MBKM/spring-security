/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dto;

import lombok.Data;

/**
 *
 * @author rebel
 */
@Data
public class MailRequest {
    
    private String name;
    private String to;
    private String from;
    private String subject;
}
