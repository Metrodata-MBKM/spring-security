package com.mbkm.hr.repositories;

import com.mbkm.hr.models.Employee;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
