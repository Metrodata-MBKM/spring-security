/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.credentials.Privilege;
import com.mbkm.hr.repositories.PrivilegeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class PrivilegeService extends CRUDService<PrivilegeRepository, Privilege, String> {
    @Autowired
    public PrivilegeService(PrivilegeRepository privilegeRepository){
        super.repository = privilegeRepository;
    }
}
