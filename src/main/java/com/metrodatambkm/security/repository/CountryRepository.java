package com.metrodatambkm.security.repository;

import com.metrodatambkm.security.models.hr_schema.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
