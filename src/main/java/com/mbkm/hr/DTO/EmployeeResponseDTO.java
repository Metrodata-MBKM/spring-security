/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.DTO;

import com.mbkm.hr.models.hr_schema.Employee;
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
public class EmployeeResponseDTO {
    Integer id;
    String firstName;
    String lastName;
    String email;
    String phoneNumber;
    Date hireDate;
    Double salary;
    Double commissionPct;
    String jobTitle;
    String departmentName;
    String managerName;

    public EmployeeResponseDTO(Employee e) {
        id = e.getId();
        firstName = e.getFirstName();
        lastName = e.getLastName();
        email = e.getEmail();
        phoneNumber = e.getPhoneNumber();
        hireDate = e.getHireDate();
        salary = e.getSalary();
        commissionPct = e.getCommissionPct();
        jobTitle = e.getJob().getTitle();
        departmentName = e.getDepartment().getName();
        managerName = e.getManager().getFirstName();
    }

}
