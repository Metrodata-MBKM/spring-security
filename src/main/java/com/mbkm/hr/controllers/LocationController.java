/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.models.Location;
import com.mbkm.hr.services.LocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author loisceka
 */
@RestController
@RequestMapping("api/location")
public class LocationController implements BaseController<Location, Integer> {

    @Autowired
    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    @GetMapping()
    public List<Location> getAll() {
        if (locationService.getAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
        }
        return locationService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Location getById(@PathVariable Integer id) {
        if (locationService.getById(id).isPresent()) {
            return locationService.getById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
    }

    @Override
    @PostMapping()
    public Location save(@RequestBody Location location) {
        if (locationService.getById(location.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exist !");
        }
        return locationService.save(location);
    }

    @Override
    @PutMapping("/{id}")
    public Location update(@RequestBody Location location) {
        if (locationService.getById(location.getId()).isPresent()) {
            return locationService.save(location);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
    }

    @Override
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        if (locationService.getById(id).isPresent()) {
            locationService.delete(id);
            return "ID: " + id + " Successfully deleted !";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
    }
}
