package com.mbkm.hr.controllers;

import com.mbkm.hr.models.Country;
import com.mbkm.hr.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/country")
public class CountryController implements BaseController<Country, String> {

    @Autowired
    CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    @GetMapping
    public List<Country> getAll() {
        return countryService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Country getById(@PathVariable("id") String id) {
        try {
            return countryService.getById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country with ID :" + id + "not found!");
        }
    }

    @Override
    @PostMapping
    public Country save(@RequestBody Country country) {
        if (countryService.getById(country.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate data!");
        } else {
            return countryService.save(country);
        }
    }

    @Override
    @PutMapping("/{id}")
    public Country update(@PathVariable("id") String id, @RequestBody  Country country) {
        if (countryService.getById(id).isPresent()) {
            return countryService.save(country);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Duplicate data!");
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        if (countryService.getById(id).isPresent()) {
            countryService.delete(id);
            return "Delete Successfully!";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found");
    }
}
