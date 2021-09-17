/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dtos;

import com.mbkm.hr.models.Department;
import com.mbkm.hr.models.Employee;
import com.mbkm.hr.models.Job;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author gabri
 */
@Data @AllArgsConstructor @NoArgsConstructor
@Builder
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
    private Department department;
    private Employee manager;
    
//    public EmployeeResponseDTO(Employee e){
//        id=e.getId();
//        firstName=e.getFirstName();
//        lastName=e.getLastName();
//        email=e.getEmail();
//        phoneNumber=e.getPhoneNumber();
//        hireDate=e.getHireDate();
//        salary=e.getSalary();
//        commissionPct=e.getCommissionPct();        
//        jobTitle=e.getJob().getTitle();
//        managerName=e.getManagerId().getFirstName();
//        departmentName=e.getDepartment().getName();
//    }
}
