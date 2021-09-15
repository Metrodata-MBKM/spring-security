package com.metrodatambkm.security.dtos.response;

import com.metrodatambkm.security.dtos.EntityResponse;
import com.metrodatambkm.security.entities.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CountryResponse implements EntityResponse<Country, CountryResponse> {
    private Integer id;
    private String name;
    private String region;

    public CountryResponse(Country country) {
        this.id = country.getId();
        this.name = country.getName();
        this.region = country.getRegion().getName();
    }

    @Override
    public CountryResponse create(Country country) {
        return new CountryResponse(country);
    }
}
