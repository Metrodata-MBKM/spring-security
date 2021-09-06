/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.models.Region;
import com.mbkm.hr.services.RegionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/region")
public class RegionController implements BaseController<Region, Integer>{
    
    @Autowired
    private RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }
    
    @Override
    @GetMapping
    public List<Region> getAll() {
        
        return regionService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Region getById(@PathVariable("id") Integer id) {
        if(regionService.getById(id).isPresent()){
            return regionService.getById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

    @PostMapping
    public Region save(@RequestBody Region region, Authentication authentication) {
        System.out.println(authentication.getAuthorities().toString());
        if (regionService.getById(region.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate data!");
        } else {
            return regionService.save(region);
        }
    }

    @Override
    @PutMapping("/{id}")
    public Region update(@PathVariable("id") Integer id, @RequestBody Region region) {
        if(regionService.getById(id).isPresent()){
            return regionService.save(region);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

    @Override
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Integer id) {
        if(regionService.getById(id).isPresent()){
            regionService.delete(id);
            return ("Region with ID: " + id + " Successfully deleted");
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

    @Override
    public Region save(Region object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
