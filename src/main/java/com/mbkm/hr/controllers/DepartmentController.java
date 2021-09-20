/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.models.Department;
import com.mbkm.hr.services.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author loisceka
 */
@RestController
@RequestMapping("/department")
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'EMPLOYEE')")
public class DepartmentController implements BaseController<Department, Integer> {

    @Autowired
    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<Department> getAll() {
        return departmentService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public Department getById(@PathVariable Integer id) {
        try {
            return departmentService.getById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with ID: " + id + " Not Found");
        }
    }

    @Override
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public Department save(@RequestBody Department department) {
        departmentService.findByName(department.getName());
        return departmentService.save(department);
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_DATA')")
    public Department update(@PathVariable("id") Integer id, @RequestBody Department department) {
        if (departmentService.getById(id).isPresent()) {
            return departmentService.save(department);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Department with ID: " + department.getId() + " Not Found");
        }
    }

//    @Override
//    @DeleteMapping("/{id}")
//    public Department delete(@PathVariable Integer id) {
//        if (departmentService.delete(id)) {
//            return departmentService.getById(id).get();
//        } else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with ID: " + id + " Not Found");
//        }
//    }
    @Override
    @PreAuthorize("hasAuthority('DELETE_DATA')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        if (departmentService.delete(id)) {
            return ("Department with ID: " + id + "Deleted Successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department with ID: " + id + " Not Found");
        }
    }

}
