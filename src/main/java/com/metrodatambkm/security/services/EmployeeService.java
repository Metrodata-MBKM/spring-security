package com.metrodatambkm.security.services;


import com.metrodatambkm.security.dto.*;
import com.metrodatambkm.security.models.hr_schema.Department;
import com.metrodatambkm.security.models.hr_schema.Employee;
import com.metrodatambkm.security.models.hr_schema.Job;
import com.metrodatambkm.security.models.hr_schema.Location;
import com.metrodatambkm.security.repository.DepartmentRepository;
import com.metrodatambkm.security.repository.EmployeeRepository;
import com.metrodatambkm.security.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

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

    public EmployeeDTO delete(Long id) {
        Employee employee = findById(id);
        repository.deleteById(id);
        return employeeBuilder(employee);
    }

    public List<EmployeeDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(employee -> employeeBuilder(employee))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getById(Long id) {
        Employee employee = findById(id);
        return employeeBuilder(employee);
    }

    public EmployeeDTO create(Employee employee) {
        if (employee.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee already exists!");
        }
        return employeeBuilder(repository.save(employee));
    }

    public EmployeeDTO update(Long id, Employee employee) {
        findById(id);
        Job job = jobRepository.getById(employee.getJob().getId());
        employee.setId(id);
        employee.setJob(job);
        return employeeBuilder(repository.save(employee));
    }

    private Employee findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found!"));
    }

    private EmployeeDTO employeeBuilder(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .manager(employee.getManager())
                .salary(employee.getSalary())
                .commissionPct(employee.getCommissionPct())
                .job(employee.getJob())
                .department(employee.getDepartment())
                .hireDate(employee.getHireDate())
                .build();
    }
}

