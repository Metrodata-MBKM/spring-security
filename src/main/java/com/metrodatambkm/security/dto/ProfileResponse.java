package com.metrodatambkm.security.dto;

import lombok.Data;

@Data
public class ProfileResponse {
    private String fullname;
    private String email;
    private String phoneNumber;

    public ProfileResponse() {

    }

    public ProfileResponse(String fullname, String email, String phoneNumber) {
        this.fullname = fullname;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
