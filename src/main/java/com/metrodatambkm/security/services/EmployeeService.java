package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dtos.request.EmployeeRequest;
import com.metrodatambkm.security.dtos.response.EmployeeResponse;
import com.metrodatambkm.security.entities.Employee;
import com.metrodatambkm.security.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends CRUDService<Employee, EmployeeRequest, EmployeeResponse, Integer>{

    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    JobService jobService;

    public EmployeeService(EmployeeRepository repository) {
        super.repository = repository;
    }

    @Override
    public Employee convert(EmployeeRequest employeeRequest) {
        return new Employee(
                employeeRequest.getId(),
                employeeRequest.getFirstName(),
                employeeRequest.getLastName(),
                employeeRequest.getEmail(),
                employeeRequest.getPhone(),
                employeeRequest.getHireDate(),
                employeeRequest.getSalary(),
                employeeRequest.getCommissionPct(),
                jobService.getOne(employeeRequest.getJob()),
                employeeService.getOne(employeeRequest.getManager()),
                departmentService.getOne(employeeRequest.getDepartment())
        );
    }
}
