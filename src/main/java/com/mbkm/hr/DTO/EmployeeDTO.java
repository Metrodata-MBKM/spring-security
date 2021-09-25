/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.DTO;

import com.mbkm.hr.models.hr_schema.Department;
import com.mbkm.hr.models.hr_schema.Job;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Lenovo-PC
 */
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class EmployeeDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private Job job;
    private Double salary;
    private Double commissionPct;
    private Department department; 
}