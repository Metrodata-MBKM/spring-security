package com.mbkm.hr.services;

import com.mbkm.hr.DTO.EmployeeDTO;
import com.mbkm.hr.models.hr_schema.Employee;
import com.mbkm.hr.repositories.EmployeeRepository;
import com.mbkm.hr.repositories.JobRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends CRUDService<EmployeeRepository, Employee, Integer>{
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        super.repository = employeeRepository;
    }
    
    public List<EmployeeDTO> getAlle(){
        List<EmployeeDTO> employeeDTO = new ArrayList<>();
        for (Employee employee : getAll()) {
            employeeDTO.add(new EmployeeDTO(employee));
        }
        return employeeDTO;
    }
}
