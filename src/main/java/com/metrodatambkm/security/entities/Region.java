package com.metrodatambkm.security.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "region")
@Data @AllArgsConstructor @NoArgsConstructor
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "region", fetch = FetchType.LAZY)
    private List<Country> countries;

    public Region(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
