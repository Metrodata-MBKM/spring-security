package com.metrodatambkm.security.repository;


import com.metrodatambkm.security.models.hr_schema.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
