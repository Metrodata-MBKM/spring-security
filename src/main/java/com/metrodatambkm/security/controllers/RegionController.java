/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.models.hr_schema.Region;
import com.metrodatambkm.security.services.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Dony Tri P
 */
@RestController
@RequestMapping("/region")
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class RegionController {

    Logger logger = LoggerFactory.getLogger(RegionController.class);

    @Autowired
    private RegionService regionService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<Region> getAllRegion() {
        System.out.println("Test");
        return regionService.getAll();
    }
}
