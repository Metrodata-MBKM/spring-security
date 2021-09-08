/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.repositories;

import com.mbkm.hr.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author rebel
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
     Role findByName(String name);
    
}
