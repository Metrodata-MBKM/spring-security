/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dtos;

import com.mbkm.hr.models.Employee;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author rebel
 */
@Data @AllArgsConstructor @NoArgsConstructor
public class EmployeeResponseDTO {
    
    private Integer employeeId;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Date hireDate;
    private String departmentName;
    private Double commissionPct;
    private Double salary;
    private String jobTitle;
    private String managerName;
    
    public EmployeeResponseDTO(Employee e){
        employeeId = e.getId();
        email = e.getEmail();
        firstName = e.getFirstName();
        lastName = e.getLastName();
        phoneNumber = e.getPhoneNumber();
        hireDate = e.getHireDate();
        departmentName = e.getDepartment().getName();
        commissionPct = e.getCommissionPct();
        salary = e.getSalary();
        jobTitle = e.getJob().getTitle();
        managerName = e.getManagerId().getFirstName(); 
    }
}
