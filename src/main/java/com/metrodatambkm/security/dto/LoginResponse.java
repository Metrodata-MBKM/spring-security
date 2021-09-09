package com.metrodatambkm.security.dto;

import com.metrodatambkm.security.models.AppUserRole;
import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;
    private String role;

    public LoginResponse(String token, AppUserRole appUserRole) {
        this.accessToken = token;
        this.role = appUserRole.name();
    }
}
