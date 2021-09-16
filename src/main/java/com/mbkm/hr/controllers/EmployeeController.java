package com.mbkm.hr.controllers;

import com.mbkm.hr.dtos.request.EmployeeRequestDTO;
import com.mbkm.hr.dtos.response.EmployeeResponseDTO;
import com.mbkm.hr.models.hrschemas.Employee;
import com.mbkm.hr.services.EmployeeService;
import com.mbkm.hr.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
public class EmployeeController implements BaseController<Employee, Integer>{

    @Autowired
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @GetMapping("")
//    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
    
    @GetMapping("/dto")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public List<EmployeeResponseDTO> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @Override
    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('READ_DATA')")
    public Employee getById(@PathVariable Integer id) {
        try{
            return employeeService.getById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + id + " Not Found");
        }
    }
    
    @GetMapping("/dto/{id}")
    @PreAuthorize("hasAuthority('READ_DATA')")
    public EmployeeResponseDTO getByIdEmployee(@PathVariable Integer id) {
        try{
            return employeeService.getByIdEmployee(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + id + " Not Found");
        }
    }

    @Override
    @PostMapping
//    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public Employee save(@RequestBody Employee employee) {
        if(employeeService.getById(employee.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee with ID: " + employee.getId() + " Is Already Exist");
        }else{
            return employeeService.save(employee);
        }
    }
    
    @PostMapping("/dto")
    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public EmployeeResponseDTO create(@RequestBody EmployeeRequestDTO employeeRequestDTO){
//        if(employeeService.getById(employeeRequestDTO.getId()).isPresent()) {
//            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee with ID: " + employeeRequestDTO.getId() + " Is Already Exist");
//        }else{
//            return employeeService.create(employeeRequestDTO);
//        }
        System.out.println(employeeRequestDTO.toString());
        return employeeService.create(employeeRequestDTO);
    }
    
    @PutMapping("/dto/{id}")
    @PreAuthorize("hasAuthority('EDIT_DATA')")
    public EmployeeResponseDTO update(@PathVariable("id") Integer id, 
            @RequestBody EmployeeRequestDTO employeeRequestDTO){
//        if(!employeeService.getById(id).isPresent()) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + employeeRequestDTO.getId() + "Not found");
//        }else{
//            return employeeService.create(employeeRequestDTO);
//        }
        System.out.println(employeeRequestDTO.toString());
        return employeeService.create(employeeRequestDTO);
    }

    @Override
    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('EDIT_DATA')")
    public Employee update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        if (!employeeService.getById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + employee.getId() + " Not Found");
        } else {
            return employeeService.save(employee);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_DATA')")
    public String delete(@PathVariable("id") Integer id) {
        if (employeeService.getById(id).isPresent()) {
            employeeService.delete(id);
            return ("Employee with ID: " + id + " Deleted Successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee ID: " + id + " Not Found");
        }
    }
}
