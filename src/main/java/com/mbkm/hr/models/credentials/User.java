/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.models.credentials;

import com.mbkm.hr.models.credentials.Role;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email", length = 50, unique = true)
    private String email;
    
    @Column(name = "enabled")
    private boolean enabled = false;

    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Role> roles;
    
    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
        this.enabled = false;
    }
}
