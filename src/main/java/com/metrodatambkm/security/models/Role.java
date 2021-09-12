package com.metrodatambkm.security.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metrodatambkm.security.models.credentials.AppUser;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<AppUser> users;

    @ManyToMany
    private List<Privilege> privileges;

    public Role(String name) {
        this.name = name;
    }

    public Role(Long id) {
        this.id = id;
    }
}
