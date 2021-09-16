package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.models.hr_schema.Country;
import com.metrodatambkm.security.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.getAll();
    }

    @PostMapping
    public Country save(@RequestBody Country country) {
        return countryService.save(country);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable("id") Long id) {
        countryService.delete(id);
    }

    @PutMapping("/{id}")
    public void updateCountry(@PathVariable("id") Long id, @RequestBody Country country) {
        countryService.update(country);
    }

    @GetMapping("/{id}")
    public Country getCountry(@PathVariable("id") Long id) {
        return countryService.getById(id);
    }
}
