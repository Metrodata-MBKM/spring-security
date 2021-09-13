/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author loisceka
 */
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class ProfileDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
//    private String department;
//    private String job;
//    private String manager;
}
