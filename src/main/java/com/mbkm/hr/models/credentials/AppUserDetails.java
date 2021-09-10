/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.models.credentials;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author WahyuKu
 */
public class AppUserDetails implements UserDetails {
    
    private User user;
    private Role role;

    public AppUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> userRole = user.getRoles();
        Collection<GrantedAuthority> authenticated = new ArrayList<>();
        
        for (Role role : userRole) {
            authenticated.add(new SimpleGrantedAuthority("ROLE_"+role.getName().toUpperCase()));
            for (Privilege privilege : role.getPrivileges()) {
                authenticated.add(new SimpleGrantedAuthority(privilege.getName().toUpperCase()));
            }
        }
        return authenticated;
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
