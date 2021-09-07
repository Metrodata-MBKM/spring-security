/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.dto.EmployeeRequestDTO;
import com.mbkm.hr.dto.EmployeeResponseDTO;
import com.mbkm.hr.dto.RegisterDTO;
import com.mbkm.hr.models.Employee;
import com.mbkm.hr.services.EmployeeService;
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
 * @author loisceka
 */
@RestController
@RequestMapping("employee")
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
public class EmployeeController implements BaseController<Employee, Integer> {

    @Autowired
    EmployeeService employeeService;

    @Override
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping()
    public List<EmployeeRequestDTO> listAll() {
        if (employeeService.getAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
        }
        return employeeService.getAllReq();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id) {
        if (employeeService.getById(id).isPresent()) {
            return employeeService.getByIds(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
    }

//    @PostMapping()
//    public EmployeeResponseDTO saveEmployeeRequestDTO(@RequestBody RegisterDTO registerDTO){
//        return employeeService.registerEmployee(registerDTO);
//    }
    @Override
    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        if (employeeService.getById(employee.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee with ID: " + employee.getId() + " Is Already Exist");
        } else {
            return employeeService.save(employee);
        }
    }

    @Override
    @PatchMapping("/{id}")
    public Employee update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        if (!employeeService.getById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + employee.getId() + " Not Found");
        } else {
            return employeeService.save(employee);
        }
    }

    @Override
    @DeleteMapping
    public Employee delete(Integer id) {
        if (employeeService.delete(id)) {
            return employeeService.getById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee ID: " + id + " Not Found");
        }
    }

}
