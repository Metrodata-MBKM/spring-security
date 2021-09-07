/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.services;

import com.metrodatambkm.security.models.Department;
import com.metrodatambkm.security.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author gabri
 */
public class DepartmentService extends CRUDService<DepartmentRepository, Department, Integer>{
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        super.repository = departmentRepository;
    }
}
