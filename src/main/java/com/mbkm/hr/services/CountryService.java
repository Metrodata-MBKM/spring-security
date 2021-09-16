package com.mbkm.hr.services;

import com.mbkm.hr.models.Country;
import com.mbkm.hr.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CountryService extends CRUDService<CountryRepository, Country, Integer> {

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        super.repository = countryRepository;
    }
    public boolean findByName(String name) {
        if (repository.findByName(name) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data Already Exist..");
        }
        return true;
    }
}
