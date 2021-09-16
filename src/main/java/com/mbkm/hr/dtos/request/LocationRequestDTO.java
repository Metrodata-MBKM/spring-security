/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.dtos.request;

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
public class LocationRequestDTO {
    private Integer id;
    private String city;
    private String postalCode;
    private String province;
    private String street;
    private String countryId;
}
