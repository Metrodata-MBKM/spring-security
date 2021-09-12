/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.models;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author gabri
 */
@Entity
@Table(name="users")
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    
    @Column(name="username", length=50)
    private String username;
    
    @Column(name="password", length=255)
    private String password;
    
    @Column(name="email", length=50)
    private String email;
    
    @MapsId
    @OneToOne
    @JoinColumn(name="id")
    private Employee emploeyee;
    
    
    @Column(name = "enabled", columnDefinition ="default false")
    private boolean enabled = false;
    
    @ManyToMany(fetch=FetchType.EAGER)
    Set<Role> roles;
//    private Long id;
//    
//    private String username;
//    
//    private String email;
//    
//    private String password;
//    
//    private List<String> authorities;
//    
//    private boolean active;
}
