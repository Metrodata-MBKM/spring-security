/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.Department;
import com.mbkm.hr.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author loisceka
 */
@Service
public class DepartmentService extends CRUDService<DepartmentRepository, Department, Integer>{
    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository){
        super.repository = departmentRepository;
    }
}
