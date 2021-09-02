/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "departments")
@Data
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "department_id")
    private int id;
    
    @Column(name = "department_name", length = 30)
    private String name;
    
    @Column(name = "manager_id", length = 30)
    private Integer manager;
    
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @OneToMany
    @JsonBackReference
    private Set<Employee> employees;
}
