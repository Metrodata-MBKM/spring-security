/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.repositories;

import com.mbkm.hr.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hp
 */
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
}
