package com.metrodatambkm.security.dto;

import lombok.Data;

@Data
public class EmployeeRequestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Long department;
    private Long job;
    private Long manager;
}
