package com.metrodatambkm.security.services;

import com.metrodatambkm.security.models.hr_schema.Country;
import com.metrodatambkm.security.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService  {
    @Autowired
    CountryRepository countryRepository;
}
