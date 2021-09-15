/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.models.credentials;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mbkm.hr.models.credentials.Role;
import com.mbkm.hr.models.hrschemas.Employee;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author WahyuKu
 */
@Entity
@Table(name = "users")
@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "employee_id")
    private Integer id;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled = false;
    
    @ManyToMany(fetch = FetchType.EAGER)
    Set<Role> roles;
    
//    @JsonBackReference
    @MapsId
    @OneToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
