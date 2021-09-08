package com.metrodatambkm.security.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ConfirmationResponse {
    private boolean status;
    private String message;
}
