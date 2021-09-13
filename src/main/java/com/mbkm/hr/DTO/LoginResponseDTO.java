/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.DTO;

import com.mbkm.hr.models.Role;
import java.util.List;
import java.util.Set;
import lombok.*;

/**
 *
 * @author Dony Tri P
 */
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class LoginResponseDTO {
    private String accessToken;
    private List<String> authorities;
}
