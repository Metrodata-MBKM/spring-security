package com.metrodatambkm.security.dtos.request;

import com.metrodatambkm.security.dtos.response.DepartmentResponse;
import com.metrodatambkm.security.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class EmployeeRequest extends BaseRequest<Integer>{
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date hireDate;
    private Integer job;
    private Double salary;
    private Double commissionPct;
    private Integer manager;
    private Integer department;

}
