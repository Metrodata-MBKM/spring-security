package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dtos.request.LocationRequest;
import com.metrodatambkm.security.dtos.response.LocationResponse;
import com.metrodatambkm.security.entities.Location;
import com.metrodatambkm.security.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService extends CRUDService<Location, LocationRequest, LocationResponse, Integer>{

    @Autowired
    CountryService countryService;

    @Autowired
    public LocationService(LocationRepository repository) {
        super.repository = repository;
    }

    @Override
    public Location convert(LocationRequest request) {
        return new Location(
                request.getId(),
                request.getStreetAddress(),
                request.getPostalCode(),
                request.getCity(),
                request.getStateProvince(),
                countryService.getOne(request.getCountry())
        );
    }
}
