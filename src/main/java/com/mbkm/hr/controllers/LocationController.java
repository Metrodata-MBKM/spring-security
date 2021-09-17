/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.dtos.request.LocationRequestDTO;
import com.mbkm.hr.dtos.response.LocationResponseDTO;
import com.mbkm.hr.models.hrschemas.Location;
import com.mbkm.hr.services.LocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
 * @author hp
 */
@RestController
@RequestMapping("/location")
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
public class LocationController implements BaseController<Location, Integer> {

    @Autowired
    private LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @Override
    @GetMapping()
//    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<Location> getAll() {
        if (locationService.getAll().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
        }
        return locationService.getAll();
    }
    
    @GetMapping("/dto")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<LocationResponseDTO> getAllLocation(){
        return locationService.getAllLocation();
    }

    @Override
    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('READ_DATA')")
    public Location getById(@PathVariable Integer id) {
        if (locationService.getById(id).isPresent()) {
            return locationService.getById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
    }
    
    @GetMapping("/dto/{id}")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public LocationResponseDTO getByIdLocation(@PathVariable Integer id) {
        if (locationService.getById(id).isPresent()) {
            return locationService.getByIdLocation(id);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
    }

    @Override
    @PostMapping()
//    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public Location save(@RequestBody Location location) {
        if (locationService.getById(location.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exist !");
        }
        return locationService.save(location);
    }
    
    @PostMapping("/dto")
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public LocationResponseDTO create(@RequestBody LocationRequestDTO locationRequestDTO){
        System.out.println(locationRequestDTO.toString());
        return locationService.create(locationRequestDTO);
    }

    @Override
    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('EDIT_DATA')")
    public Location update(@PathVariable("id") Integer id, @RequestBody Location location) {
        if (locationService.getById(id).isPresent()) {
            return locationService.save(location);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
    }
    
    @PutMapping("/dto/{id}")
    @PreAuthorize("hasAuthority('EDIT_DATA')")
    public LocationResponseDTO update(@PathVariable("id") Integer id, 
            @RequestBody LocationRequestDTO locationRequestDTO){
        System.out.println(locationRequestDTO.toString());
        return locationService.create(locationRequestDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_DATA')")
    public String delete(@PathVariable Integer id) {
        if (locationService.getById(id).isPresent()) {
            locationService.delete(id);
            return "ID: " + id + " Successfully deleted !";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found !");
    }
}
