/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.dto.EmployeeRequestDTO;
import com.mbkm.hr.dto.EmployeeResponseDTO;
import com.mbkm.hr.dto.RegisterDTO;
import com.mbkm.hr.models.Department;
import com.mbkm.hr.models.Employee;
import com.mbkm.hr.repositories.DepartmentRepository;
import com.mbkm.hr.repositories.EmployeeRepository;
import com.mbkm.hr.repositories.JobRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author loisceka
 */
@Service
public class EmployeeService extends CRUDService<EmployeeRepository, Employee, Integer>{

    private EmployeeRepository employeeRepository;
    private JobRepository jobRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
    }

    //GET BY ID
    public Optional<Employee> getById(Integer id) {
        return employeeRepository.findById(id);
    }

    public Employee getByIds(Integer id) {
        return employeeRepository.findById(id).get();
    }

    public boolean checkIfPresent(Integer id) {
        return getById(id).isPresent();
    }

//    //Find ALL
//    public List<Employee> getAll() {
//        List<EmployeeRequestDTO> emp = new ArrayList<>();
//        for (Employee employee : employeeRepository.findAll()) {
//            emp.add(
//                    new EmployeeRequestDTO(
//                    employee.getFirstName(),
//                    employee.getLastName(),
//                    employee.getJob().getTitle(),
//                    employee.getDepartment() == null? "": employee.getDepartment().getLocation().getCountry().getName(),
//                    employee.getDepartment() == null? "": employee.getDepartment().getLocation().getCountry().getRegion().getName()
//                    )
//            );
//        }
//        return employeeRepository.findAll();
//    }
    public EmployeeResponseDTO registerEmployee(RegisterDTO registerDTO) {
        Employee employee = saveEmployee(registerDTO);

        return createRegistrationResult(employeeRepository.save(employee));
    }

    public List<EmployeeRequestDTO> getAllReq() {
        List<EmployeeRequestDTO> emp = new ArrayList<>();
        for (Employee e : employeeRepository.findAll()) {
            emp.add(createEmployeeRequestDTO(e));
        }
        return emp;
    }

    public EmployeeRequestDTO createEmployeeRequestDTO(Employee e) {
        return new EmployeeRequestDTO(
                e.getFirstName(),
                e.getLastName(),
                e.getManager() == null ? "" : e.getManager().getFirstName() + " " + e.getManager().getLastName(),
                e.getJob().getTitle(),
                e.getDepartment() == null ? "" : e.getDepartment().getLocation().getCountry().getName(),
                e.getDepartment() == null ? "" : e.getDepartment().getLocation().getCountry().getRegion().getName()
        );
    }

    public Employee saveEmployee(RegisterDTO registerDTO) {
        // CHECKING EMPLOYEE DATA BASED ON EMAIL
        Employee oldEmployee = employeeRepository.findByEmail(registerDTO.getEmail());
         if (oldEmployee != null) {
             throw new ResponseStatusException(HttpStatus.CONFLICT, "Data already exist !");
        }
        // ADDING EMPLOYEE
        return new Employee(
                registerDTO.getId(),
                registerDTO.getFirstName(),
                registerDTO.getLastName(),
                registerDTO.getEmail(),
                registerDTO.getPhoneNumber(),
                registerDTO.getHireDate(),
                jobRepository.getById(registerDTO.getJob()),
                registerDTO.getSalary(),
                registerDTO.getCommissionPct(),
                departmentRepository.getById(registerDTO.getDepartment()),
                employeeRepository.findById(registerDTO.getManagerId()).get()
        );
    }

    public EmployeeResponseDTO createRegistrationResult(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPhoneNumber(),
                employee.getHireDate(),
                employee.getJob().getTitle(),
                employee.getSalary(),
                employee.getCommissionPct(),
                employee.getManager().getFirstName() + " " + employee.getManager().getLastName(),
                employee.getDepartment().getName()
        );
    }

}
