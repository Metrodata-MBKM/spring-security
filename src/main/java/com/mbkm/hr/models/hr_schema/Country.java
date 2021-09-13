package com.mbkm.hr.models.hr_schema;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "countries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @Column(name = "country_id", length = 4)
    private String id;

    @Column(name = "country_name", length = 35)
    private String name;

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
}
