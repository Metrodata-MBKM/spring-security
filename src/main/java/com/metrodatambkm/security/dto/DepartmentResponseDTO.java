package com.metrodatambkm.security.dto;

import com.metrodatambkm.security.models.hr_schema.Employee;
import com.metrodatambkm.security.models.hr_schema.Location;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class DepartmentResponseDTO {
    private Long id;
    private String name;
    private Long managerId;
    private Location location;

    public DepartmentResponseDTO(Long id, String name, Long managerId, Location location) {
        this.id = id;
        this.managerId = managerId;
        this.name = name;
        this.location = location;
    }
}
