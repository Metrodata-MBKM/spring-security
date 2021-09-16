/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.models.hr_schema.Region;
import com.metrodatambkm.security.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dony Tri P
 */
@RestController
@RequestMapping("/region")
//@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
//    @PreAuthorize("hasAuthority('READ')")
    public List<Region> getAllRegion() {
        return regionService.getAll();
    }

    @GetMapping("/{id}")
    public Region getRegion(@PathVariable("id") Long id) {
        return regionService.getById(id);
    }

    @PostMapping
    public Region save(@RequestBody Region region) {
        return regionService.save(region);
    }

    @DeleteMapping("/{id}")
    public void deleteRegion(@PathVariable("id") Long id) {
        regionService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateRegion(@PathVariable("id") Long id,@RequestBody Region region) {
        regionService.update(region);
    }
}
