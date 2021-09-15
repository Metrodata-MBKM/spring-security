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
 * @author Kevitha
 */
@Data @NoArgsConstructor @AllArgsConstructor @Builder
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

//    public EmployeeResponseDTO(Employee e) {
//        this.id = e.getId();
//        this.firstName = e.getFirstName();
//        this.lastName = e.getLastName();
//        this.email = e.getEmail();
//        this.phoneNumber = e.getPhoneNumber();
//        this.hireDate = e.getHireDate();
//        this.salary = e.getSalary();
//        this.commissionPct = e.getCommissionPct();
//        this.jobTitle = e.getJob().getTitle();
//        this.managerName = e.getManagerId() == null ? "" : e.getManagerId().getFirstName();
//        this.departmentName = e.getDepartment().getName();
//    }
//
//    
}
