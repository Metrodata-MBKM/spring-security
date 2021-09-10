package com.metrodatambkm.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProfileRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private Date birthDate;
}
