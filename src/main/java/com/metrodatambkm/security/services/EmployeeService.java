package com.metrodatambkm.security.services;


import com.metrodatambkm.security.dto.EmployeeRequest;
import com.metrodatambkm.security.dto.RequestRegister;
import com.metrodatambkm.security.dto.ResponseEmployee;
import com.metrodatambkm.security.models.hr_schema.Employee;
import com.metrodatambkm.security.repository.DepartmentRepository;
import com.metrodatambkm.security.repository.EmployeeRepository;
import com.metrodatambkm.security.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Employee saveEmployee(Employee employee) {
        return employee;
    }

    public List<ResponseEmployee> getAllEmployee() {
        List<ResponseEmployee> employees = new ArrayList<>();

        for (Employee employee : repository.findAll()) {
            employees.add(
                    new ResponseEmployee(
                            employee.getFirstName() + " " + employee.getLastName(),
                            employee.getManagerId().getFirstName() + " " + employee.getManagerId().getLastName(),
                            employee.getJob().getTitle(),
                            employee.getDepartment().getLocation().getCountry().getName(),
                            employee.getDepartment().getLocation().getCountry().getRegion().getName()
                    )
            );
        }
        return employees;
    }

    public Employee registerEmployee(RequestRegister register) {

        Employee employee = new Employee(
                register.getFirstName(),
                register.getLastName(),
                register.getEmail(),
                register.getPhoneNumber(),
                getCurrentDate(),
                register.getSalary(),
                register.getComission(),
                jobRepository.getById(register.getJob()),
                departmentRepository.getById(register.getDepartment()),
                repository.findById(register.getManager()).get()
        );
        Employee employeeResult = repository.save(employee);
        return repository.findById(employeeResult.getId()).get();
    }

    private String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
}

