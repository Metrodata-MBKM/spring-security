/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dto.DepartmentRequestDTO;
import com.metrodatambkm.security.dto.DepartmentResponseDTO;
import com.metrodatambkm.security.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hp
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public List<DepartmentResponseDTO> getAllDepartment() {
        return departmentService.getAll();
    }

//    @PostMapping
//    public DepartmentResponseDTO save(@RequestBody DepartmentRequestDTO requestDTO) {
//        return departmentService.save(requestDTO);
//    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable("id") Long id) {
        departmentService.delete(id);
    }

//    @PutMapping("/{id}")
//    public void updateDepartment(@PathVariable("id") Long id, @RequestBody DepartmentRequestDTO requestDTO) {
//        departmentService.update(requestDTO);
//    }

    @GetMapping("/{id}")
    public DepartmentResponseDTO getDepartment(@PathVariable("id") Long id) {
        return departmentService.getById(id);
    }
}
