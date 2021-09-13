package com.metrodatambkm.security.entities.credentials;

import com.metrodatambkm.security.entities.Profile;
import com.metrodatambkm.security.entities.permission.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
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

    @Column(name = "enabled", columnDefinition ="tinyint(1) default 0")
    private boolean enabled = false;

    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Profile profile;

    @OneToOne
    @PrimaryKeyJoinColumn
    private VerificationToken verificationToken;

    public User(Integer id, String username, String password, String email, boolean enabled, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = enabled;
        this.roles = roles;
    }
}
