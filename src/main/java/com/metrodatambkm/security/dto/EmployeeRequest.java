package com.metrodatambkm.security.dto;

import lombok.Data;

@Data
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long department;
    private Long job;
    private Long manager;
}
