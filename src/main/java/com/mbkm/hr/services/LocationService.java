/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.Location;
import com.mbkm.hr.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author loisceka
 */
@Service
public class LocationService extends CRUDService<LocationRepository, Location, Integer> {

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        super.repository = locationRepository;
    }

}
