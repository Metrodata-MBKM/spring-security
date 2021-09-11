/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.repository;

import com.metrodatambkm.security.models.hr_schema.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author loisceka
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
