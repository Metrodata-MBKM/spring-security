package com.mbkm.hr.controllers;

import com.mbkm.hr.DTO.EmployeeDTO;
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
public class EmployeeController implements BaseController<Employee, Integer>{

    @Autowired
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @GetMapping("")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
    
    @GetMapping("/dto")
    public List<EmployeeDTO> getAlle() {
        return employeeService.getAlle();
    }

    @Override
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id) {
        try{
            return employeeService.getById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + id + " Not Found");
        }
    }

    @Override
    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        if(employeeService.getById(employee.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee with ID: " + employee.getId() + " Is Already Exist");
        }else{
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
    public String delete(Integer id) {
        if (employeeService.delete(id)) {
            return ("Employee with ID: " + id + " Deleted Successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee ID: " + id + " Not Found");
        }
    }
}
