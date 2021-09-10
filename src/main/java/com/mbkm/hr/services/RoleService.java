/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.credentials.Role;
import com.mbkm.hr.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class RoleService extends CRUDService<RoleRepository, Role, Integer> {
    @Autowired
    public RoleService(RoleRepository roleRepository){
        super.repository = roleRepository;
    }
}
