/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dto.DepartmentRequestDTO;
import com.metrodatambkm.security.dto.DepartmentResponseDTO;
import com.metrodatambkm.security.models.hr_schema.Department;
import com.metrodatambkm.security.models.hr_schema.Employee;
import com.metrodatambkm.security.models.hr_schema.Location;
import com.metrodatambkm.security.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hp
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private LocationService locationService;

    @Autowired
    private EmployeeService employeeService;

    public Department findById(Long department) {
        return departmentRepository.getById(department);
    }

    public List<DepartmentResponseDTO> getAll() {
        List<DepartmentResponseDTO> departmentResponseDTOS = new ArrayList<>();
        List<Department> departments = departmentRepository.findAll();
        for (Department department : departments) {
            DepartmentResponseDTO departmentResponseDTO = new DepartmentResponseDTO();
            departmentResponseDTO.setId(department.getId());
            departmentResponseDTO.setName(department.getName());
            departmentResponseDTO.setManagerId(department.getManager().getId());
            departmentResponseDTO.setLocation(department.getLocation());
            departmentResponseDTOS.add(departmentResponseDTO);
        }
        return departmentResponseDTOS;
    }

//    public Department saveDepartment(DepartmentRequestDTO requestDTO) {
//        Location location = locationService.getById(requestDTO.getLocation());
//        Employee employee = employeeService.getById(requestDTO.getManager());
//        Department department = new Department(
//                requestDTO.getId(),
//                requestDTO.getName(),
//                employee,
//                location);
//        return departmentRepository.save(department);
//    }

//    public DepartmentResponseDTO save(DepartmentRequestDTO requestDTO) {
//        Employee employee = employeeService.getById(requestDTO.getManager());
//        Location location = locationService.getById(requestDTO.getLocation());
//        Department department = new Department(
//                requestDTO.getId(),
//                requestDTO.getName(),
//                employee,
//                location
//        );
//        departmentRepository.save(department);
//        return new DepartmentResponseDTO(
//                requestDTO.getId(),
//                requestDTO.getName(),
//                employee.getId(),
//                location
//        );
//    }

    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

//    public void update(DepartmentRequestDTO request) {
//        Employee employee = employeeService.getById(request.getManager());
//        Location location = locationService.getById(request.getLocation());
//        Department department = new Department(
//                request.getId(),
//                request.getName(),
//                employee,
//                location
//        );
//        departmentRepository.save(department);
//    }

    public DepartmentResponseDTO getById(Long id) {
        Department department = departmentRepository.getById(id);
        return new DepartmentResponseDTO(
                department.getId(),
                department.getName(),
                department.getManager().getId(),
                department.getLocation()
        );
    }
}
