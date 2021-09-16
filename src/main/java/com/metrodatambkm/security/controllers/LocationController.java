/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.models.hr_schema.Location;
import com.metrodatambkm.security.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author loisceka
 */
@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping
    public List<Location> getAllLocation() {
        return locationService.getAll();
    }

    @PostMapping
    public Location save(@RequestBody Location location) {
        return locationService.save(location);
    }

    @PutMapping("/{id}")
    public Location updateLocation(@PathVariable("id") Long id, @RequestBody Location location) {
        return locationService.update(location);
    }

    @GetMapping("/{id}")
    public Location getLocation(@PathVariable("id") Long id) {
        return locationService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        locationService.delete(id);
    }
}
