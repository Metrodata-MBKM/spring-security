package com.metrodatambkm.security.entities;

import com.metrodatambkm.security.entities.credentials.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@Data @AllArgsConstructor @NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @JoinColumn(name = "manager")
    @ManyToOne
    private User manager;

    @JoinColumn(name = "location")
    @ManyToOne
    private Location location;
}
