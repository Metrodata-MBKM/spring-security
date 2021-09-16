package com.mbkm.hr.services;

import com.mbkm.hr.dtos.EmployeeRequestDTO;
import com.mbkm.hr.dtos.EmployeeResponseDTO;
import com.mbkm.hr.models.Employee;
import com.mbkm.hr.repositories.DepartmentRepository;
import com.mbkm.hr.repositories.EmployeeRepository;
import com.mbkm.hr.repositories.JobRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService extends CRUDService<EmployeeRepository, Employee, Integer>{
    
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private JobRepository jobRepository;
    
    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, 
            DepartmentRepository departmentRepository, 
            JobRepository jobRepository){
        super.repository = employeeRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.jobRepository = jobRepository;
    }
    
    public List<EmployeeResponseDTO> getAllEmployee(){
        List<EmployeeResponseDTO> employeeDTO = new ArrayList<>();
        for (Employee employee : getAll()) {
            employeeDTO.add(new EmployeeResponseDTO(employee));
        }   
        return employeeDTO;
    } 
    
    public EmployeeResponseDTO create(EmployeeRequestDTO employeeRequestDTO){
        Employee employee = new Employee(
                null,
                employeeRequestDTO.getFirstName(),
                employeeRequestDTO.getLastName(),
                employeeRequestDTO.getEmail(),
                employeeRequestDTO.getPhoneNumber(),
                employeeRequestDTO.getHireDate(),
                employeeRequestDTO.getSalary(),
                employeeRequestDTO.getCommissionPct(),
                jobRepository.getById(employeeRequestDTO.getJobId()),
                departmentRepository.getById(employeeRequestDTO.getDepartmentId()),
                employeeRepository.getById(employeeRequestDTO.getManagerId())
        );
        System.out.println(employee.toString());
        Employee employeeResult = employeeRepository.save(employee);
        Employee employees = employeeRepository.getById(employeeResult.getId());
        
        return new EmployeeResponseDTO(
                employees.getId(),
                employees.getEmail(), 
                employees.getFirstName(), 
                employees.getLastName(), 
                employees.getPhoneNumber(), 
                employees.getHireDate(),
                employees.getDepartment().getName(),
                employees.getCommissionPct(), 
                employees.getSalary(),         
                employees.getJob().getTitle(), 
                employees.getManagerId().getFirstName());        
    }
    
       public EmployeeResponseDTO update(EmployeeRequestDTO employeeRequestDTO){
        Employee employee = new Employee(
                employeeRequestDTO.getId(),
                employeeRequestDTO.getFirstName(),
                employeeRequestDTO.getLastName(),
                employeeRequestDTO.getEmail(),
                employeeRequestDTO.getPhoneNumber(),
                employeeRequestDTO.getHireDate(),
                employeeRequestDTO.getSalary(),
                employeeRequestDTO.getCommissionPct(),
                jobRepository.getById(employeeRequestDTO.getJobId()),
                departmentRepository.getById(employeeRequestDTO.getDepartmentId()),
                employeeRepository.getById(employeeRequestDTO.getManagerId())
        );
        System.out.println(employee.toString());
        Employee employeeResult = employeeRepository.save(employee);
        Employee employees = employeeRepository.getById(employeeResult.getId());
        
        return new EmployeeResponseDTO(
                employees.getId(),
                employees.getEmail(), 
                employees.getFirstName(), 
                employees.getLastName(), 
                employees.getPhoneNumber(), 
                employees.getHireDate(),
                employees.getDepartment().getName(),
                employees.getCommissionPct(), 
                employees.getSalary(),         
                employees.getJob().getTitle(), 
                employees.getManagerId().getFirstName());        
    }
       
        public EmployeeRequestDTO getByIdEmployee(Integer id){
        Employee employees = getById(id).get();
        return new EmployeeRequestDTO(
                employees.getId(),
                employees.getFirstName(), 
                employees.getLastName(), 
                employees.getEmail(), 
                employees.getPhoneNumber(), 
                employees.getHireDate(),
                employees.getJob().getId(), 
                employees.getSalary(), 
                employees.getCommissionPct(), 
                employees.getManagerId().getId(),
                employees.getDepartment().getId());
    }
       
       
        
    
}
