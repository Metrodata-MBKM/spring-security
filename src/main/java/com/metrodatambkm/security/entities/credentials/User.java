package com.metrodatambkm.security.entities.credentials;

import com.metrodatambkm.security.entities.permission.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "enabled", columnDefinition ="default false")
    private boolean enabled = false;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;
}
