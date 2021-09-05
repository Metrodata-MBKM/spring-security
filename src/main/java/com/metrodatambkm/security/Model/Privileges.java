/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.Model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author Dony Tri P
 */
@Entity
@Table(name = "privileges")
public class Privileges {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "privilege_id", length = 5)
    private Integer id;

    @Column(name = "privilage_name", length = 50)
    private String name;
    
    @ManyToMany(mappedBy = "rolePrivileges")
    Set<Roles> roles;
}
