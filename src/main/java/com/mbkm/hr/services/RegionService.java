/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.hrschemas.Region;
import com.mbkm.hr.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dony Tri P
 */
@Service
public class RegionService extends CRUDService<RegionRepository, Region, Integer>{
    
    @Autowired
    public RegionService(RegionRepository regionRepository){
        super.repository = regionRepository;
    }
}
