package com.metrodatambkm.security.dto;

import com.metrodatambkm.security.models.hr_schema.Department;
import com.metrodatambkm.security.models.hr_schema.Employee;
import com.metrodatambkm.security.models.hr_schema.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private Job job;
    private Employee manager;
    private Department department;
    private Double commissionPct;
    private Double salary;
}
