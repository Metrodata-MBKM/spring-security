package com.mbkm.hr.models.hr_schema;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @Basic(optional = false)
    @Column(name = "country_id")
    private String id;
    @Column(name = "country_name")
    private String name;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country", fetch = FetchType.EAGER)
    private Set<Location> locations;
    @JoinColumn(name = "region_id", referencedColumnName = "region_id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Region region;
}
