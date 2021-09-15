package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.request.RegionRequest;
import com.metrodatambkm.security.dtos.response.RegionResponse;
import com.metrodatambkm.security.entities.Region;
import com.metrodatambkm.security.services.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
//@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
public class RegionController extends BasicRestController<Region, RegionRequest, RegionResponse, Integer> {

    @Autowired
    public RegionController(RegionService service) {
        super.service = service;
    }

}
