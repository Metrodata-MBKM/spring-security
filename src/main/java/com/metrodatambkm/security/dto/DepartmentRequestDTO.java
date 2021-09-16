package com.metrodatambkm.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentRequestDTO {
    private Long id;
    private String name;
    private Long manager;
    private Long location;
}
