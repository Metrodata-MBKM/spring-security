package com.metrodatambkm.security.service;

import com.metrodatambkm.security.model.RegionModel;
import com.metrodatambkm.security.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegionServiceImpl implements BaseService<RegionModel, Long> {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public void add(RegionModel region) {
        regionRepository.save(region);
    }

    @Override
    public List<RegionModel> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public Optional<RegionModel> getById(Long id) {
        return regionRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        regionRepository.deleteById(id);
    }

    @Override
    public RegionModel change(RegionModel region) {
        RegionModel target = regionRepository.getById(region.getId());
        target.setName(region.getName());
        return regionRepository.save(region);
    }

}
