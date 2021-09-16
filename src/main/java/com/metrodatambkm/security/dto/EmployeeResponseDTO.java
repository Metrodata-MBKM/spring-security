package com.metrodatambkm.security.dto;

import com.metrodatambkm.security.models.hr_schema.Department;
import com.metrodatambkm.security.models.hr_schema.Job;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Long manager;
    private Department department;
    private Job job;
    private String email;
    private String phoneNumber;
}
