package com.metrodatambkm.security.dtos;

import com.metrodatambkm.security.entities.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProfileResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private Date birthDate;

    public ProfileResponse(Profile profile){
        this.firstName = profile.getFirstName();
        this.lastName = profile.getLastName();
        this.phone = profile.getPhone();
        this.birthDate = profile.getBirthDate();
    }
}
