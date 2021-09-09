/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.Employee;
import com.mbkm.hr.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author loisceka
 */
@Service
public class EmployeeService extends CRUDService<EmployeeRepository, Employee, Integer> {

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        super.repository = employeeRepository;
    }
    
}
