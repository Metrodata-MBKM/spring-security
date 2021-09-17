/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.dtos.request.DepartmentRequestDTO;
import com.mbkm.hr.dtos.response.DepartmentResponseDTO;
import com.mbkm.hr.models.hrschemas.Department;
import com.mbkm.hr.services.DepartmentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author hp
 */
@RestController
@RequestMapping("/department")
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
public class DepartmentController implements BaseController<Department, Integer>{
    
    @Autowired
    DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    @GetMapping
    @ResponseBody
//    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<Department> getAll() {
        return departmentService.getAll();
    }
    
    @GetMapping("/dto")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<DepartmentResponseDTO> getAllDepartment(){
        return departmentService.getAllDepartment();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public Department getById(@PathVariable Integer id) {
        try {
            return departmentService.getById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job with ID: " + id + " Not Found");
        }
    }
    
    @GetMapping("/dto/{id}")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public DepartmentResponseDTO getByIdDepartment(@PathVariable Integer id) {
        if (departmentService.getById(id).isPresent()) {
            return departmentService.getByIdDepartment(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
    }

    @Override
    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public Department save(@RequestBody Department department) {
//        return departmentService.save(department);
        if (departmentService.getById(department.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Job with ID: " + department.getId() + " Is Already Exist");
        } else {
            return departmentService.save(department);
        }
    }
    
    @PostMapping("/dto")
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public DepartmentRequestDTO create(@RequestBody DepartmentRequestDTO departmentRequestDTO){
        System.out.println(departmentRequestDTO.toString());
        return departmentService.create(departmentRequestDTO);
    }
    
    @PutMapping("/dto/{id}")
    @PreAuthorize("hasAuthority('EDIT_DATA')")
    public DepartmentRequestDTO update(@PathVariable("id") Integer id, 
            @RequestBody DepartmentRequestDTO departmentRequestDTO){
        System.out.println(departmentRequestDTO.toString());
        return departmentService.create(departmentRequestDTO);
    }

//    @Override
    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('EDIT_DATA')")
    public Department update(@PathVariable("id") Integer id, @RequestBody Department department) {
        if (departmentService.getById(id).isPresent()) {
            return departmentService.save(department);
        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Job with ID: " + department.getId() + " Not Found");
        }
    }

//    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_DATA')")
    public String delete(@PathVariable Integer id) {
        if (departmentService.delete(id)){
            return ("Job with ID: " + id + "Deleted Successfully");
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Job with ID: " + id + " Not Found");
        }
    }
    
}
