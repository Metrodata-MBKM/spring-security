/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.dto;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author gabri
 */
@Data
@Builder
public class ProfileDTO {
    private String username;
    private String email;
    private String firsName;
    private String lastName;
    
}
