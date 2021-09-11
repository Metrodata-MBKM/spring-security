package com.metrodatambkm.security.dto;

import lombok.Data;

@Data
public class ProfileResponse {
    private String fullname;
    private String email;
    private String birthDate;
}
