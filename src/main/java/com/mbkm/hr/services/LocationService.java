/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.dtos.request.LocationRequestDTO;
import com.mbkm.hr.dtos.response.LocationResponseDTO;
import com.mbkm.hr.models.hrschemas.Location;
import com.mbkm.hr.repositories.CountryRepository;
import com.mbkm.hr.repositories.LocationRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author loisceka
 */
@Service
public class LocationService extends CRUDService<LocationRepository, Location, Integer> {

    private CountryRepository countryRepository;
    private LocationRepository locationRepository;
    
    private CountryService countryService;
    
    @Autowired
    public LocationService(LocationRepository locationRepository, 
            CountryService countryService, 
            CountryRepository countryRepository) {
        super.repository = locationRepository;
        this.countryService = countryService;
        this.countryRepository = countryRepository;
        this.locationRepository = locationRepository;
    }
    
    public List<LocationResponseDTO> getAllLocation() {
        List<LocationResponseDTO> locationDTO = new ArrayList<>();
        
        for (Location location : getAll()) {
            locationDTO.add(new LocationResponseDTO(location, 
                    location.getCountry()));
        }
        return locationDTO;
    }
    
    public LocationResponseDTO getByIdLocation(Integer id){
        Location location = getById(id).get();
        return new LocationResponseDTO(location.getId(), 
                location.getCity(), 
                location.getPostalCode(), 
                location.getProvince(), 
                location.getStreet(), 
                location.getCountry());
    }
    
    public LocationResponseDTO create(LocationRequestDTO locationRequestDTO){
        Location location = new Location(
                locationRequestDTO.getId(), 
                locationRequestDTO.getCity(), 
                locationRequestDTO.getPostalCode(), 
                locationRequestDTO.getProvince(), 
                locationRequestDTO.getStreet(), 
                countryRepository.getById(locationRequestDTO.getCountryId())
        );
        
        System.out.println(location.toString());
        Location locationResult = locationRepository.save(location);
        Location locations = locationRepository.getById(locationResult.getId());
        
        return getByIdLocation(locations.getId());
    }

}
