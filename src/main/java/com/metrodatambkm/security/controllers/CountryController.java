package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.request.CountryRequest;
import com.metrodatambkm.security.dtos.response.CountryResponse;
import com.metrodatambkm.security.entities.Country;
import com.metrodatambkm.security.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController extends BasicRestController<Country, CountryRequest, CountryResponse, Integer> {

    @Autowired
    public CountryController(CountryService service) {
        super.service = service;
    }
}
