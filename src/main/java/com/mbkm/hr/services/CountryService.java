package com.mbkm.hr.services;

import com.mbkm.hr.models.hrschemas.Country;
import com.mbkm.hr.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends CRUDService<CountryRepository, Country, String> {
    @Autowired
    public CountryService(CountryRepository countryRepository) {
        super.repository = countryRepository;
    }
}
