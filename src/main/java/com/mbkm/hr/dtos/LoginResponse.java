/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dtos;

import com.mbkm.hr.models.Role;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rebel
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class LoginResponse {
    private String AccessToken;
    private Set<Role> authorities;
}
