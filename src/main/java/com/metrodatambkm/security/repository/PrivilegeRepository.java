package com.metrodatambkm.security.repository;

import com.metrodatambkm.security.model.PrivilegeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<PrivilegeModel, Long> {
}
