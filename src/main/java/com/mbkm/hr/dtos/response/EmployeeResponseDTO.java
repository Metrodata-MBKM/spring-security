/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dtos.response;

import com.mbkm.hr.models.hrschemas.Department;
import com.mbkm.hr.models.hrschemas.Employee;
import com.mbkm.hr.models.hrschemas.Job;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hp
 */
@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class EmployeeResponseDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private Double salary;
    private Double commissionPct;
    private Job job;
    private Employee manager;
    private Department department;
    
    public EmployeeResponseDTO(Employee e, Job job, Employee manager, Department department){
        id = e.getId();
        firstName = e.getFirstName();
        lastName = e.getLastName();
        email = e.getEmail();
        phoneNumber = e.getPhoneNumber();
        hireDate = e.getHireDate();
        salary = e.getSalary();
        commissionPct = e.getCommissionPct();
        this.job = job;
        this.manager = manager;
        this.department = department;
    }
}
