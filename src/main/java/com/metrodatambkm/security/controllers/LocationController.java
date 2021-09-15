package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.request.LocationRequest;
import com.metrodatambkm.security.dtos.response.LocationResponse;
import com.metrodatambkm.security.entities.Location;
import com.metrodatambkm.security.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController extends BasicRestController<Location, LocationRequest, LocationResponse, Integer>{

    @Autowired
    public LocationController(LocationService service) {
        super.service = service;
    }
}
