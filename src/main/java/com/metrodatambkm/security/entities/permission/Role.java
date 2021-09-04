package com.metrodatambkm.security.entities.permission;

import com.metrodatambkm.security.entities.permission.Privilege;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Data @AllArgsConstructor @NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToMany
    private Set<Privilege> privileges;

}
