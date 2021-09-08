/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.DTO;

import lombok.*;

/**
 *
 * @author Dony Tri P
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterRequest {
    private String email, username, password;
}
