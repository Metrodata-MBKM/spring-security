package com.mbkm.hr.controllers;

import com.mbkm.hr.models.hrschemas.Country;
import com.mbkm.hr.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/country")
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
public class CountryController implements BaseController<Country, String> {

    @Autowired
    CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    @GetMapping
    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<Country> getAll() {
        return countryService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public Country getById(@PathVariable("id") String id) {
        try {
            return countryService.getById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Country with ID :" + id + "not found!");
        }
    }

    @Override
    @PostMapping(path = "",consumes = { MediaType.APPLICATION_JSON_VALUE })
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public Country save(@RequestBody Country country) {
        if (countryService.getById(country.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate data!");
        } else {
            return countryService.save(country);
        }
    }

    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDIT_DATA')")
    public Country update(@PathVariable("id") String id, @RequestBody  Country country) {
        if (countryService.getById(id).isPresent()) {
            return countryService.save(country);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Duplicate data!");
        }
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_DATA')")
    public String delete(@PathVariable("id") String id) {
        if (countryService.getById(id).isPresent()) {
            countryService.delete(id);
            return "Delete Successfully!";
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found");
    }
}
