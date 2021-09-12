/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.dto;

import com.metrodatambkm.security.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author gabri
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterResponseDTO {
    private String email, username;
    private  boolean enabled;
    
    public RegisterResponseDTO(String email, String username){
        this.email=email;
        this.username=username;
    }
    
    public RegisterResponseDTO generate(User user){
        return new RegisterResponseDTO(user.getEmail(), user.getUsername());
    }
}
