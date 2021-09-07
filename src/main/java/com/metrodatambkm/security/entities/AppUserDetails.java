package com.metrodatambkm.security.entities;

import com.metrodatambkm.security.entities.permission.Privilege;
import com.metrodatambkm.security.entities.permission.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.metrodatambkm.security.entities.credentials.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class AppUserDetails implements UserDetails {
    private User user;

    public AppUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> userRole = user.getRoles();
        Collection<GrantedAuthority> authorities = new ArrayList<>(userRole.size());

        for(Role role: userRole){

            System.out.println("Getting role..");
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName().toUpperCase()));

            for (Privilege privilege: role.getPrivileges()) {
                authorities.add(new SimpleGrantedAuthority(privilege.getName().toUpperCase()));
                System.out.println("Authorities = " + privilege.getName().toUpperCase());
            }

        }

        return authorities;
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
