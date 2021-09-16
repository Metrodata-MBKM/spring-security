/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.services;

import com.metrodatambkm.security.models.hr_schema.Location;
import com.metrodatambkm.security.models.hr_schema.Location;
import com.metrodatambkm.security.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author loisceka
 */
@Service
public class LocationService  {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Location save(Location location) {
        System.out.println(location);
        return locationRepository.save(location);
    }

    public void delete(Long id) {
        locationRepository.deleteById(id);
    }

    public Location update(Location location) {
        return locationRepository.save(location);
    }

    public Location getById(Long location) {
        return locationRepository.getById(location);
    }
}
