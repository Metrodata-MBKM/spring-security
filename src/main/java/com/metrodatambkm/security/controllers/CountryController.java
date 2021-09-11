package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.models.hr_schema.Country;
import com.metrodatambkm.security.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController{

    @Autowired
    CountryService countryService;
}
