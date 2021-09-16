package com.mbkm.hr.controllers;

import com.mbkm.hr.DTO.EmployeeDTO;
import com.mbkm.hr.DTO.EmployeeRequestDTO;
import com.mbkm.hr.DTO.EmployeeResponseDTO;
import com.mbkm.hr.models.hr_schema.Employee;
import com.mbkm.hr.services.EmployeeService;
import com.mbkm.hr.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController{

    @Autowired
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAll() {
        return employeeService.getAll();
    }
    
    @GetMapping("/{id}")
    public EmployeeDTO getById(@PathVariable Integer id) {
        try{
            return employeeService.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + id + " Not Found");
        }
    }

    @PostMapping
    public EmployeeDTO create(@RequestBody Employee employee) {
        return employeeService.create(employee);
        
    }
    
    @PutMapping("/{id}")
    public EmployeeDTO update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        if (employeeService.findById(employee.getId())==null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + employee.getId() + " Not Found");
        } else {
            return employeeService.update(id,employee);
        }
    }
    
    @DeleteMapping("/{id}")
    public EmployeeDTO delete(@PathVariable("id") Integer id){
        if (employeeService.findById(id)==null){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        } else{
            return employeeService.delete(id);
        }
    }
}
