/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.repositories;

import com.metrodatambkm.security.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gabri
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
    
}
