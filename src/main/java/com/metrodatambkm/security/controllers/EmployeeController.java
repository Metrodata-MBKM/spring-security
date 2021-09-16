package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dto.EmployeeDTO;
import com.metrodatambkm.security.dto.EmployeeRequestDTO;
import com.metrodatambkm.security.dto.EmployeeResponseDTO;
import com.metrodatambkm.security.dto.RequestRegister;
import com.metrodatambkm.security.models.hr_schema.Employee;
import com.metrodatambkm.security.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public Employee register(@RequestBody RequestRegister register) {
        return employeeService.registerEmployee(register);
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return employeeService.getAll();
    }

    @PostMapping
    public EmployeeDTO save(@RequestBody Employee employee) {
        return employeeService.create(employee);
    }

    @GetMapping("/{id}")
    public EmployeeDTO getById(@PathVariable("id") Long id) {
        return employeeService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        employeeService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Employee employee) {
        employeeService.update(id, employee);
    }
}
