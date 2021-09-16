package com.metrodatambkm.security.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LocationRequest extends BaseRequest<Integer> {
    private String streetAddress;
    private String city;
    private String stateProvince;
    private String postalCode;
    private Integer country;
}
