/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dtos;

import lombok.Data;

/**
 *
 * @author hp
 */
@Data
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String managerName;
    private String countryName;
    private String regionName;

    public EmployeeDTO(String firstName, String lastName,String manager_name, String jobTitle, String countryName, String regionName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.managerName = manager_name;
        this.jobTitle = jobTitle;
        this.countryName = countryName;
        this.regionName = regionName;
    }
}
