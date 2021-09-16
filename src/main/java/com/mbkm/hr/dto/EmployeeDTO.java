/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dto;

import com.mbkm.hr.models.Department;
import com.mbkm.hr.models.Job;
import java.sql.Date;
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
public class EmployeeDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private Job job;
    private Double commisionPct;
    private Department department;
    private Double salary;
   
}
