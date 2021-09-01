/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hp
 */
@Entity
@Table(name = "regions")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "department_id")
    private int id;
    
    @Column(name = "department_name", length = 30)
    private String name;
    
    @Column(name = "manager_id", length = 30)
    private int manager;
    
    @Column(name = "location_id", length = 30)
    private int department;
}
