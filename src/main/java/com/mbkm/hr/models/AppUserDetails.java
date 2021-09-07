/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author loisceka
 */
public class AppUserDetails implements UserDetails {

    private User user;

    public AppUserDetails(User user) {
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<Role> userRoles = user.getRoles();
        Collection<GrantedAuthority> auth = new ArrayList<>();

        for (Role role : userRoles) {
            auth.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));
            for (Privilege rolePrivilege : role.getPrivileges()) {
                auth.add(new SimpleGrantedAuthority(rolePrivilege.getName().toUpperCase()));
            }
        }
        return auth;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
