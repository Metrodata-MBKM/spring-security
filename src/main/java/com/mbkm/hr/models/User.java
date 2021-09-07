/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author WahyuKu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    
    private Long id;
    
    private String username;
    
    private String email;
    
    private String password;
    
    private List<String> authorities;
    
    private boolean active;
}
