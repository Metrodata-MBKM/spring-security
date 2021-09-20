/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.dto.EmployeeDTO;
import com.mbkm.hr.models.Employee;
import com.mbkm.hr.repositories.EmployeeRepository;
import com.mbkm.hr.repositories.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author loisceka
 */
@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private UserRepository userRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<EmployeeDTO> getAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employee -> employeeBuilder(employee))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getById(Integer id) {
        return employeeBuilder(findById(id));
    }

    public Employee findById(Integer id) {
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    }

    public EmployeeDTO create(Employee employee) {
        if (employee.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee already exist");
        }
        return employeeBuilder(employeeRepository.save(employee));
    }

    public EmployeeDTO update(Integer id, Employee employee) {
        findById(id);
        employee.setId(id);
        return employeeBuilder(employeeRepository.save(employee));
    }

    public EmployeeDTO delete(Integer id) {
        Employee emp = findById(id);
        employeeRepository.deleteById(id);
        return employeeBuilder(emp);
    }

    private EmployeeDTO employeeBuilder(Employee employee) {
        return EmployeeDTO
                .builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail())
                .job(employee.getJob())
                .department(employee.getDepartment())
                .commisionPct(employee.getCommissionPct())
                .hireDate(employee.getHireDate())
                .salary(employee.getSalary())
                .build();
    }
}
