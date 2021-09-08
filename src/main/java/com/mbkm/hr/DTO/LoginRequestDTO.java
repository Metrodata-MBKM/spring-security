/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Dony Tri P
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class LoginRequestDTO {
    private String username;
    private String password;
}
