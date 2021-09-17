/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.dtos.request.DepartmentRequestDTO;
import com.mbkm.hr.dtos.response.DepartmentResponseDTO;
import com.mbkm.hr.models.hrschemas.Department;
import com.mbkm.hr.repositories.DepartmentRepository;
import com.mbkm.hr.repositories.EmployeeRepository;
import com.mbkm.hr.repositories.LocationRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class DepartmentService extends CRUDService<DepartmentRepository, Department, Integer>{
    
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private LocationRepository locationRepository;
    
    @Autowired
    public DepartmentService(EmployeeRepository employeeRepository, 
            DepartmentRepository departmentRepository, 
            LocationRepository locationRepository){
        super.repository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.locationRepository = locationRepository;
    }
    
    public List<DepartmentResponseDTO> getAllDepartment() {
        List<DepartmentResponseDTO> departmentDTO = new ArrayList<>();
        
        for (Department department : getAll()) {
            departmentDTO.add(new DepartmentResponseDTO(department, 
                    department.getLocation(), department.getManager()));
        }
        
        return departmentDTO;
    }
    
    public DepartmentResponseDTO getByIdDepartment(Integer id){
        Department department = getById(id).get();
        return new DepartmentResponseDTO(department.getId(), 
                department.getName(), 
                department.getLocation(),
                department.getManager());
    }
    
    public DepartmentRequestDTO create(DepartmentRequestDTO departmentRequestDTO){
        Department department = new Department(
                departmentRequestDTO.getId(),
                departmentRequestDTO.getName(), 
                employeeRepository.getById(departmentRequestDTO.getManagerId()), 
                locationRepository.getById(departmentRequestDTO.getLocationId()));
        
        System.out.println(department.toString());
        Department departmentResult = departmentRepository.save(department);
        Department departments = departmentRepository.getById(departmentResult.getId());
        
        return new DepartmentRequestDTO(departments.getId(), 
                departments.getName(), 
                departments.getLocation().getId(), 
                departments.getManager().getId()); 
   }
}
