package com.metrodatambkm.security.dtos.response;

import com.metrodatambkm.security.entities.credentials.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class RegisterResponse {
    private String email, username;
    private boolean enabled;

    public RegisterResponse(String email, String username) {
        this.email = email;
        this.username = username;
    }

    public RegisterResponse generate(User user){
        return new RegisterResponse(user.getEmployee().getEmail(), user.getUsername());
    }
}
