/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.repositories;

import com.metrodatambkm.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author gabri
 */
public interface RoleRepository extends JpaRepository<Role, Integer>{
    Role findByName(String name);
}
