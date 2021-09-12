/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.dto;

import com.metrodatambkm.security.models.Role;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author gabri
 */
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private String accessToken;
    private Set<String> authorities;
}
