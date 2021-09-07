package com.metrodatambkm.security.services;

import com.metrodatambkm.security.entities.Region;
import com.metrodatambkm.security.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public List<Region> getAll(){
        return  regionRepository.findAll();
    }

    public Region getById(Integer id){
        return regionRepository.findById(id).get();
    }

    public Region create(Region region){
        return regionRepository.save(region);
    }
}
