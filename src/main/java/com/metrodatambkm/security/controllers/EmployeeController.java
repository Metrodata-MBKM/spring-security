package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.request.EmployeeRequest;
import com.metrodatambkm.security.dtos.response.EmployeeResponse;
import com.metrodatambkm.security.entities.Employee;
import com.metrodatambkm.security.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController extends BasicRestController<Employee, EmployeeRequest, EmployeeResponse, Integer>{

    @Autowired
    public EmployeeController(EmployeeService service) {
        super.service = service;
    }
}
