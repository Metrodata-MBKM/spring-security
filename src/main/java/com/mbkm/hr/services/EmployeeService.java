package com.mbkm.hr.services;

import com.mbkm.hr.dtos.EmployeeRequestDTO;
import com.mbkm.hr.dtos.EmployeeResponseDTO;
import com.mbkm.hr.models.Employee;
import com.mbkm.hr.repositories.DepartmentRepository;
import com.mbkm.hr.repositories.EmployeeRepository;
import com.mbkm.hr.repositories.JobRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmployeeService{

    private final JobRepository jobRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<EmployeeResponseDTO> getAll() {
        return employeeRepository.findAll()
                .stream()   
                .map(employee -> builder(employee))
                .collect(Collectors.toList());
    }

    public EmployeeResponseDTO create(Employee employee) {
        return builder(employeeRepository.save(employee));
    }
    
    public EmployeeResponseDTO builder(Employee employee){
        return EmployeeResponseDTO
                .builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .job(employee.getJob())
                .salary(employee.getSalary())
                .department(employee.getDepartment())
                .commissionPct(employee.getCommissionPct())
                .hireDate(employee.getHireDate())
                .build();
    }
    
    public EmployeeResponseDTO getById(Integer id){
        Employee employee = findById(id);
        return builder(employee);
    }

    private Employee findById(Integer id){
        return employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee Not Found"));
    }
    
    public EmployeeResponseDTO update(Integer id, Employee employee){
        findById(id);
        
        employee.setId(id);
        
        return builder(employeeRepository.save(employee));
    }
    
    public EmployeeResponseDTO delete(Integer id){
        Employee employee = findById(id);
        
        employeeRepository.deleteById(id);
        
        return builder(employee); 
    }
    
    
}
