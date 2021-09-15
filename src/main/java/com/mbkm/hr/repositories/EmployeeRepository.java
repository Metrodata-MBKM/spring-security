package com.mbkm.hr.repositories;

import com.mbkm.hr.dtos.EmployeeResponseDTO;
import com.mbkm.hr.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Employee findByFirstName(String firstname);
}
