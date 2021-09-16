/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.Privilege;
import com.mbkm.hr.repositories.PrivilegeRepository;
import com.mbkm.hr.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author loisceka
 */
@Service
public class PrivilegeService extends CRUDService<PrivilegeRepository, Privilege, Integer> {

    @Autowired
    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        super.repository = privilegeRepository;
    }

    public boolean findByName(String name) {
        if (repository.findByName(name) != null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data Already Exist..");
        }
        return true;
    }
}
