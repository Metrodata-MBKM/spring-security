package com.mbkm.hr.controllers;

import com.mbkm.hr.dtos.EmployeeRequestDTO;
import com.mbkm.hr.dtos.EmployeeResponseDTO;
import com.mbkm.hr.models.Employee;
import com.mbkm.hr.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employee")

public class EmployeeController implements BaseController<Employee, Integer>{

    @Autowired
    EmployeeService employeeService;
    private EmployeeRequestDTO employeeResponseDTO;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    @GetMapping("")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }
    
    @GetMapping("/dto")
    public List<EmployeeResponseDTO> getAllEmployee() {
        return employeeService.getAllEmployee();
    }

    @Override
    @GetMapping("/{id}")
    public Employee getById(@PathVariable Integer id) {
        try{
            return employeeService.getById(id).get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + id + " Not Found");
        }
    }
    
    @GetMapping("/dto/{id}")
    public EmployeeRequestDTO getByIdDTO(@PathVariable Integer id) {
          return employeeService.getByIdEmployee(id);
    }

    @Override
    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        if(employeeService.getById(employee.getId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Employee with ID: " + employee.getId() + " Is Already Exist");
        }else{
            return employeeService.save(employee);
        }
    }
    
    
    @PostMapping("/dto")
    public EmployeeResponseDTO create(@RequestBody EmployeeRequestDTO employeeRequestDTO) {   
            System.out.println(employeeRequestDTO.toString());
            return employeeService.create(employeeRequestDTO);
            
    }
   

    @Override
    @PutMapping("/{id}")
    public Employee update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        if (!employeeService.getById(id).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee with ID: " + employee.getId() + " Not Found");
        } else {
            return employeeService.save(employee);
        }
    }
    
    @PutMapping("/dto/{id}")
    public EmployeeResponseDTO update(@PathVariable("id") Integer id, @RequestBody EmployeeRequestDTO employeeRequestDTO) {  
            System.out.println(employeeRequestDTO.toString());
            return employeeService.update(employeeRequestDTO);
        }

    @Override
    @DeleteMapping
    public String delete(Integer id) {
        if (employeeService.delete(id)) {
            return ("Employee with ID: " + id + " Deleted Successfully");
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee ID: " + id + " Not Found");
        }
    }
}
