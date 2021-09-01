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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author loisceka
 */
@Entity
@Table(name = "regions")
@Getter @Setter @NoArgsConstructor
public class Region {
   
    @Id
    @Basic(optional = false)
    @Column(name = "region_id", length = 11)
    private Integer id;
    
    @Column(name = "region_name", length = 25)
    private String name;
    
    @OneToMany(mappedBy = "region")
    private Set<Country> countries;
}
