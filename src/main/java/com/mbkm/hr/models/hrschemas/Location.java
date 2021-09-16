/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.models.hrschemas;

import com.mbkm.hr.models.hrschemas.Department;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "location_id", length = 11)
    private Integer id;

    @Column(name = "street_address", length = 40)
    private String street;

    @Column(name = "postal_code", length = 12)
    private String postalCode;

    @Column(name = "city", length = 30)
    private String city;

    @Column(name = "state_province", length = 25)
    private String province;
    
    @JsonManagedReference
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Country country;
    
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "location")
    private Set<Department> departments;

    public Location(String street, String postalCode, String city, String province, Country country) {
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public Location(Integer id, String street, String postalCode, String city, String province, Country country) {
        this.id = id;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.province = province;
        this.country = country;
    }
    
    
}
