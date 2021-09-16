package com.mbkm.hr.controllers;

import com.mbkm.hr.dtos.EmployeeResponseDTO;
import com.mbkm.hr.models.Employee;
import com.mbkm.hr.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employee")

public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeResponseDTO> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponseDTO getById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }

    @PostMapping
    public EmployeeResponseDTO create(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDTO update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }
    
    @DeleteMapping("/{id}")
    public EmployeeResponseDTO delete(@PathVariable("id") Integer id) {
        return employeeService.delete(id);
    }
}
