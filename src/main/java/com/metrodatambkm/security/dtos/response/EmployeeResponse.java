package com.metrodatambkm.security.dtos.response;

import com.metrodatambkm.security.dtos.EntityResponse;
import com.metrodatambkm.security.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
public class EmployeeResponse implements EntityResponse<Employee, EmployeeResponse> {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Date hireDate;
    private String job;
    private Double salary;
    private Double commissionPct;
    private String manager;
    private String department;

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.hireDate = employee.getHireDate();
        this.job = employee.getJob().getName();
        this.salary = employee.getSalary();
        this.commissionPct = employee.getCommissionPct();
        this.manager = employee.getManager().getFirstName() + " " + employee.getManager().getLastName();
        this.department = employee.getDepartment().getName();
    }

    @Override
    public EmployeeResponse create(Employee employee) {
        return new EmployeeResponse(employee);
    }
}
