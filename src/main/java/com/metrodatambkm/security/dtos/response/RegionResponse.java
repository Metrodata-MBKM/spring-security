package com.metrodatambkm.security.dtos.response;

import com.metrodatambkm.security.dtos.EntityResponse;
import com.metrodatambkm.security.entities.Region;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class RegionResponse implements EntityResponse<Region, RegionResponse> {
    private Integer id;
    private String name;

    public RegionResponse(Region region) {
        this.id = region.getId();
        this.name = region.getName();
    }

    @Override
    public RegionResponse create(Region region) {
        return new RegionResponse(region);
    }
}
