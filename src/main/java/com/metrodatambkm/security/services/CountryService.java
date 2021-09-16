package com.metrodatambkm.security.services;

import com.metrodatambkm.security.models.hr_schema.Country;
import com.metrodatambkm.security.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAll() {
        return countryRepository.findAll();
    }

    public Country save(Country country) {
        return countryRepository.save(country);
    }

    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

    public void update(Country country) {
        countryRepository.save(country);
    }

    public Country getById(Long id) {
        return countryRepository.getById(id);
    }
}
