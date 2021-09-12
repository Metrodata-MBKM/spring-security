package com.metrodatambkm.security.repository;

import com.metrodatambkm.security.model.RegionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<RegionModel, Long> {
}
