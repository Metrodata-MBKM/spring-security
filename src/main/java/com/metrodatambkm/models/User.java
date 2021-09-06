/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.models;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author rebel
 */
@Entity
@Table(name = "users")
@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer id;
    
    @Column (name = "username", length = 50, nullable = false, unique = true)
    private String username;
    
    @Column (name = "password", length = 255, nullable = false)
    private String password;
    
    @Column (name = "email", length = 50, nullable = false, unique = true)
    private String email;
    
    @ManyToMany(mappedBy = "User")
    private Set<Role> roles;
}