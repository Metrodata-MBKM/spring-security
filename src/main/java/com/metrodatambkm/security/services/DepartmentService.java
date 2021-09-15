package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dtos.request.DepartmentRequest;
import com.metrodatambkm.security.dtos.response.DepartmentResponse;
import com.metrodatambkm.security.entities.Department;
import com.metrodatambkm.security.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService extends CRUDService<Department, DepartmentRequest, DepartmentResponse, Integer>{

    @Autowired
    UserService userService;

    @Autowired
    LocationService locationService;

    public DepartmentService(DepartmentRepository repository) {
        super.repository = repository;
    }

    @Override
    public Department convert(DepartmentRequest req) {
        return new Department(
                req.getId(),
                req.getName(),
                userService.getById(req.getManager()),
                locationService.getOne(req.getLocation())
        );
    }
}
