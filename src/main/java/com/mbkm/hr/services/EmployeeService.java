package com.mbkm.hr.services;

import com.mbkm.hr.DTO.EmployeeDTO;
import com.mbkm.hr.DTO.EmployeeRequestDTO;
import com.mbkm.hr.DTO.EmployeeResponseDTO;
import com.mbkm.hr.models.hr_schema.Employee;
import com.mbkm.hr.repositories.DepartmentRepository;
import com.mbkm.hr.repositories.EmployeeRepository;
import com.mbkm.hr.repositories.JobRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService{
    
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private JobRepository jobRepository;
    
    @Autowired
     public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, JobRepository jobRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.jobRepository = jobRepository;
    }
    
    public Employee findById(Integer id) {
        return employeeRepository.getById(id);
    }
    
    public EmployeeDTO getById(Integer id){
        return employeeBuilder(findById(id));
    }
    
    public List<EmployeeDTO> getAll() {
        return employeeRepository
                .findAll()
                .stream()
                .map(employee -> employeeBuilder(employee))
                .collect(Collectors.toList());
    }
    
    public EmployeeDTO update(Integer id, Employee employee) {
        getById(id);
        employee.setId(id);
        return employeeBuilder(employeeRepository.save(employee));
    }
    
    public EmployeeDTO create(Employee employee) {
        return employeeBuilder(employeeRepository.save(employee));
    }
    
    public EmployeeDTO delete(Integer id) {
        Employee employee = findById(id);
        employeeRepository.delete(employee);
        return employeeBuilder(employee);
    }
    
    private EmployeeDTO employeeBuilder(Employee employee) {
        return EmployeeDTO
                .builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .phoneNumber(employee.getPhoneNumber())
                .email(employee.getEmail())
                .job(employee.getJob())
                .department(employee.getDepartment())
                .commissionPct(employee.getCommissionPct())
                .hireDate(employee.getHireDate())
                .salary(employee.getSalary())
                .build();
    }
    
}
