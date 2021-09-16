/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dtos.response;

import com.mbkm.hr.models.hrschemas.Country;
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
public class LocationResponseDTO {
    private Integer id;
    private String city;
    private String postalCode;
    private String province;
    private String street;
    private Country country;
    
    public LocationResponseDTO(Location l, Country countri){
        id = l.getId();
        city = l.getCity();
        postalCode = l.getPostalCode();
        province = l.getProvince();
        street = l.getStreet();
        this.country = countri;
    }
}
