/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.DTO;

import com.mbkm.hr.models.User;
import lombok.*;

/**
 *
 * @author Dony Tri P
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterResponse {
    private String email, username;
    private boolean enabled;

    public RegisterResponse(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public RegisterResponse generate(User user){
        return new RegisterResponse(user.getEmployee().getEmail(), user.getUsername());
    }
}