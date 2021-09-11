/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.services;

import com.metrodatambkm.security.models.hr_schema.Department;
import com.metrodatambkm.security.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
}
