/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.DTO;

import com.mbkm.hr.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Lenovo-PC
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterUserDTO {
    private String email, password;

}
