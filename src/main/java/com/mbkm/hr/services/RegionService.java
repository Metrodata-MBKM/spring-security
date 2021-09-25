/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.hr_schema.Region;
import com.mbkm.hr.repositories.RegionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Dony Tri P
 */
@Service
public class RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }

    public List<Region> getAllregion() {
        List<Region> regions = regionRepository.findAll();
        return regions;
    }

    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    //insert
    public Region insertData(Region region) {
        return regionRepository.save(region);
    }

    public Region getById(Integer id) {
        if (regionRepository.existsById(id)) {
            return regionRepository.findById(id).get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

    public Region deleteData(Integer id) {
        if (regionRepository.existsById(id)) {
            Region region = getById(id);
            regionRepository.delete(region);
            return region;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data ");
    }

    public Region updateData(Integer id, Region region) {
        if (regionRepository.existsById(id)) {
            Region oldRegion = getById(id);
            oldRegion.setName(region.getName());
            return regionRepository.save(oldRegion);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Data");
    }

    public boolean findByName(String name) {
        if (regionRepository.findByName(name) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Data Already Exist..");
        }
        return true;
    }

}
