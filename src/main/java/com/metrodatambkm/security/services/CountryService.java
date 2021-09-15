package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dtos.request.CountryRequest;
import com.metrodatambkm.security.dtos.response.CountryResponse;
import com.metrodatambkm.security.entities.Country;
import com.metrodatambkm.security.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService extends CRUDService<Country, CountryRequest, CountryResponse, Integer> {

    @Autowired
    RegionService regionService;

    @Autowired
    public CountryService(CountryRepository repository) {
        super.repository = repository;
        super.response = new CountryResponse();
    }

    @Override
    public Country convert(CountryRequest request) {
        return new Country(request.getId(), request.getName(), regionService.getOne(request.getRegion()));
    }
}
