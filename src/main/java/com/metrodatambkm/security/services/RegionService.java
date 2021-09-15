package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dtos.request.RegionRequest;
import com.metrodatambkm.security.dtos.response.RegionResponse;
import com.metrodatambkm.security.entities.Region;
import com.metrodatambkm.security.repositories.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService extends CRUDService<Region, RegionRequest, RegionResponse, Integer> {
    @Autowired
    public RegionService(RegionRepository repository) {
        super.repository = repository;
        super.response = new RegionResponse();
    }

    @Override
    public Region convert(RegionRequest regionRequest) {
        return new Region(regionRequest.getId(), regionRequest.getName());
    }
}
