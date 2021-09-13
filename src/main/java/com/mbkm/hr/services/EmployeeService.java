package com.mbkm.hr.services;

import com.mbkm.hr.models.hr_schema.Employee;
import com.mbkm.hr.repositories.EmployeeRepository;
import com.mbkm.hr.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends CRUDService<EmployeeRepository, Employee, Integer>{
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        super.repository = employeeRepository;
    }
}
