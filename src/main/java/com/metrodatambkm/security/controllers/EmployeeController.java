package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dto.RequestRegister;
import com.metrodatambkm.security.dto.ResponseEmployee;
import com.metrodatambkm.security.models.hr_schema.Employee;
import com.metrodatambkm.security.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController  {

    @Autowired
    private EmployeeService employeeService;



    @PostMapping("/register")
    public Employee register(@RequestBody RequestRegister register) {
        return employeeService.registerEmployee(register);
    }

    @GetMapping("/specific")
    public List<ResponseEmployee> getAllEmployee() {
        return employeeService.getAllEmployee();
    }
}
