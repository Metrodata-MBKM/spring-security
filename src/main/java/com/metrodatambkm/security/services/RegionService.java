/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.services;

import com.metrodatambkm.security.controllers.RegionController;
import com.metrodatambkm.security.models.hr_schema.Region;
import com.metrodatambkm.security.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 * @author Dony Tri P
 */
@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAll() {
        return regionRepository.findAll();
    }
}
