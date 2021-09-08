package com.metrodatambkm.security.dtos;

import com.metrodatambkm.security.entities.permission.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class LoginResponse {
    private String accessToken;
    private Set<Role> authorities;
}
