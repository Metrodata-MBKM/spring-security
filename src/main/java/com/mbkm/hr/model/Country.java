/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.model;

import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

/**
 *
 * @author loisceka
 */
@Entity
@Table(name = "countries")
@Getter @Setter @NoArgsConstructor
public class Country {
    
    @Id
    @Basic(optional = false)
    @Column(name = "country_id", length = 2)
    private Integer id;
    
    @Column(name = "country_name", length = 40)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
    
    @OneToMany(mappedBy = "country")
    private Set<Location> locations;
    
    
}
