package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.request.DepartmentRequest;
import com.metrodatambkm.security.dtos.response.DepartmentResponse;
import com.metrodatambkm.security.entities.Department;
import com.metrodatambkm.security.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department")
public class DepartmentController extends BasicRestController<Department, DepartmentRequest, DepartmentResponse, Integer> {

    @Autowired
    public DepartmentController(DepartmentService service) {
        super.service = service;
    }
}
