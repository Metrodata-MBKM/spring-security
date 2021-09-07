/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dto;

import lombok.Data;

/**
 *
 * @author loisceka
 */
@Data
public class EmployeeRequestDTO {

    private String firstName;
    private String lastName;
    private String managerId;
    private String jobTitle;
    private String regionName;
    private String countryName;

    public EmployeeRequestDTO(String firstName, String lastName, String jobTitle, String regionName, String countryName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.regionName = regionName;
        this.countryName = countryName;
    }

    public EmployeeRequestDTO(String firstName, String lastName, String managerId, String jobTitle, String regionName, String countryName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.managerId = managerId;
        this.jobTitle = jobTitle;
        this.regionName = regionName;

    }
}
