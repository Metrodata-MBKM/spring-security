package com.metrodatambkm.security.entities;

import com.metrodatambkm.security.entities.credentials.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "birth_date")
    @Nullable
    private Date birthDate;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private User user;

    public Profile(String firstName, String lastName, String phone, @Nullable Date birthDate, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.birthDate = birthDate;
        this.user = user;
    }
}