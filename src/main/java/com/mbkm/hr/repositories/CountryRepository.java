package com.mbkm.hr.repositories;

import com.mbkm.hr.models.hrschemas.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {
}
