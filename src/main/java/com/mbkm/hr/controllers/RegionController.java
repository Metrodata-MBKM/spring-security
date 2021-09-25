/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.models.hr_schema.Region;
import com.mbkm.hr.services.RegionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
 * @author Dony Tri P
 */
@RestController
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR', 'EMPLOYEE')")
@RequestMapping("/region")
public class RegionController {

    private RegionService regionService;
    
    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }
    
    @PostMapping
    public Region insert(@RequestBody Region region){
        regionService.findByName(region.getName());
        return regionService.insertData(region);
        
    }
    @PutMapping("/{id}")
    public Region update(@PathVariable Integer id, @RequestBody Region region){
        try {
            Region r = regionService.getById(id);
            return regionService.updateData(id, region);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
        }
    }
    
    @DeleteMapping("/{id}")
    public Region delete(@PathVariable Integer id){
        if(id != null){
            return regionService.deleteData(id);
        }
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }
    
    @GetMapping("/{id}")
    public Region getById(@PathVariable Integer id){
        try {
            return regionService.getById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
        }
    }
    
    @GetMapping
    public List<Region> getAllReg(){
        return regionService.getAllregion();
    }
}
