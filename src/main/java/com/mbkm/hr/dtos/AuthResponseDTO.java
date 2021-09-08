/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Kevitha
 */
@Data
@AllArgsConstructor @NoArgsConstructor
public class AuthResponseDTO {
    private String accessToken;
    private List<String> authorities;
    
}
