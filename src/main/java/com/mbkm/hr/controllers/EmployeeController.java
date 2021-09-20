/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.dto.EmployeeDTO;
import com.mbkm.hr.models.Employee;
import com.mbkm.hr.services.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author loisceka
 */
@RestController
@RequestMapping("employee")
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PreAuthorize("hasAuthority('READ_DATA')")
    @GetMapping("")
    public List<EmployeeDTO> getAll() {
        return employeeService.getAll();
    }

    @PreAuthorize("hasAuthority('READ_DATA')")
    @GetMapping("/{id}")
    public EmployeeDTO findById(@PathVariable Integer id) {
        return employeeService.getById(id);
    }
    
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    @PostMapping()
    public EmployeeDTO create(@RequestBody Employee employee){
        return employeeService.create(employee);
    }
    
    @PreAuthorize("hasAuthority('UPDATE_DATA')")
    @PutMapping("/{id}")
    public EmployeeDTO update(@PathVariable Integer id, @RequestBody Employee employee){
        return employeeService.update(id, employee);
    }
    
    @PreAuthorize("hasAuthority('DELETE_DATA')")
    @DeleteMapping("/{id}")
    public EmployeeDTO delete(@PathVariable Integer id){
        return employeeService.delete(id);
    }
}
