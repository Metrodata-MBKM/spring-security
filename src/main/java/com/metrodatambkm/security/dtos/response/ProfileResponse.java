package com.metrodatambkm.security.dtos.response;

import com.metrodatambkm.security.entities.Employee;
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

    public ProfileResponse(Employee employee){
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.phone = employee.getPhone();
        this.birthDate = employee.getHireDate();
    }
}
