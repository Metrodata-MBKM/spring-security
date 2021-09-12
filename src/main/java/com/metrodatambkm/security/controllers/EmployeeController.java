/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.models.Employee;
import com.metrodatambkm.security.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author gabri
 */
@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
public class EmployeeController  implements BaseController<Employee, Integer>{
    @Autowired
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @GetMapping("")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public Employee getById(@PathVariable Integer id) {
        try{
            return employeeService.getById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + id + " Not Found");
        }
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public Employee save(@RequestBody Employee employee) {
        if(employeeService.getById(employee.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee with ID: " + employee.getId() + " Is Already Exist");
        }else{
            return employeeService.save(employee);
        }
    }

    @Override
    @PatchMapping("/{id}")   
    @PreAuthorize("hasAuthority('EDIT_DATA')")
    public Employee update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        if (!employeeService.getById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + employee.getId() + " Not Found");
        } else {
            return employeeService.save(employee);
        }
    }

    @Override
    @DeleteMapping
    @PreAuthorize("hasAuthority('DELETE_DATA')")
    public String delete(Integer id) {
        if (employeeService.delete(id)) {
            return ("Employee with ID: " + id + " Deleted Successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee ID: " + id + " Not Found");
        }
    }
}
