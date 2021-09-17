/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dtos.response;

import com.mbkm.hr.models.hrschemas.Department;
import com.mbkm.hr.models.hrschemas.Employee;
import com.mbkm.hr.models.hrschemas.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author hp
 */
@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class DepartmentResponseDTO {
    private Integer id;
    private String name;
    private Location location;
    private Employee manager;
    
    public DepartmentResponseDTO(Department d, 
            Location location, Employee manage){
        id = d.getId();
        name = d.getName();
        this.location = location;
        this.manager = manage;
    }
}
