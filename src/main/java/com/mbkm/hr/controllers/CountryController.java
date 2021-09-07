/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.controllers;

import com.mbkm.hr.models.Country;
import com.mbkm.hr.services.CountryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author loisceka
 */
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
    @PostMapping
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public Country save(@RequestBody Country country) {
        if (countryService.getById(country.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Duplicate data!");
        } else {
            return countryService.save(country);
        }
    }

    @Override
    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_DATA')")
    public Country update(@PathVariable("id") String id, @RequestBody Country country) {
        if (countryService.getById(id).isPresent()) {
            return countryService.save(country);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Duplicate data!");
        }
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_DATA')")
    public Country delete(@PathVariable("id") String id) {
        if (countryService.getById(id).isPresent()) {
            countryService.delete(id);
            return countryService.getById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Data not found");
    }
}
