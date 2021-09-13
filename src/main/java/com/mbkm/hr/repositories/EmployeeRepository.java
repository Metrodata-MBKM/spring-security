package com.mbkm.hr.repositories;

import com.mbkm.hr.models.hrschemas.Employee;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public Employee findByFirstName(String firstname);
}
