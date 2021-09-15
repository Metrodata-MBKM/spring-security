package com.metrodatambkm.security.dtos.request;

import com.metrodatambkm.security.dtos.response.CountryResponse;
import com.metrodatambkm.security.entities.Country;
import com.metrodatambkm.security.services.RegionService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CountryRequest{

    RegionService regionService;

    private Integer id;
    private String name;
    private Integer region;

}
