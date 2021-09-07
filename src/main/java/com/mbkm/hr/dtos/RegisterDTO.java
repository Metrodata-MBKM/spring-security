/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dtos;

import java.sql.Date;
import lombok.Data;

/**
 *
 * @author hp
 */
@Data
public class RegisterDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private String job;
    private Double salary;
    private Double commissionPct;
    private Integer managerId;
    private Integer department;
}
