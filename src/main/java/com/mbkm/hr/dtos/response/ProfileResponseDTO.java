/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dtos.response;

import lombok.Builder;
import lombok.Data;

/**
 *
 * @author hp
 */
@Data
@Builder
public class ProfileResponseDTO {
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String department;
    private String job;
    private String manager;
}
