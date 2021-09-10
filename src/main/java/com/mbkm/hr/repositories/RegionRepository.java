/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.repositories;

import com.mbkm.hr.models.hrschemas.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Dony Tri P
 */
@Repository
public interface RegionRepository extends JpaRepository<Region, Integer>{
    
}