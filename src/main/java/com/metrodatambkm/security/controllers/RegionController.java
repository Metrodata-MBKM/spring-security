package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.entities.Region;
import com.metrodatambkm.security.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
public class RegionController {

    RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('READ_REGION')")
    public List<Region> getAll(){
        return regionService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_REGION')")
    public Region getById(@PathVariable Integer id){
        return regionService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_REGION')")
    public Region create(@RequestBody Region region){
        return regionService.create(region);
    }

}
