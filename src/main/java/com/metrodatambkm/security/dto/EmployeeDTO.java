/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.dto;

/**
 *
 * @author gabri
 */
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String managerName;
    private String countryName;
    private String regionName;

    public EmployeeDTO(String firstName, String lastName, String jobTitle, String managerName, String countryName, String regionName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.managerName = managerName;
        this.countryName = countryName;
        this.regionName = regionName;
    }
    
    
}
