package com.metrodatambkm.security.dtos.response;

import com.metrodatambkm.security.dtos.EntityResponse;
import com.metrodatambkm.security.entities.Country;
import com.metrodatambkm.security.entities.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class LocationResponse implements EntityResponse<Location, LocationResponse> {
    private Integer id;
    private String streetAddress;
    private String city;
    private String stateProvince;
    private String postalCode;
    private Country country;

    public LocationResponse(Location location) {
        this.id = location.getId();
        this.stateProvince = location.getStreetAddress();
        this.city = location.getCity();
        this.stateProvince = location.getStateProvince();
        this.postalCode = location.getPostalCode();
        this.country = location.getCountry();
    }

    @Override
    public LocationResponse create(Location location) {
        return new LocationResponse(location);
    }
}
