/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

/**
 *
 * @author WahyuKu
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
    
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AppUserDetailsService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetails> users = new ArrayList<>();
        List<String> authoritiesUser1 = new ArrayList<>();
        authoritiesUser1.add("ROLE_OPERATOR");
        authoritiesUser1.add("CREATE_REGION");
        
        UserDetails user1 = User.builder()
                .username("operator")
                .password(passwordEncoder.encode("operator"))
                .authorities(authorities(authoritiesUser1))
                .build();
        
        UserDetails user2 = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities("ROLE_ADMIN")
                .build();
        
        users.add(user1);
        users.add(user2);
        
        return (UserDetails) new InMemoryUserDetailsManager(users);
    }
    
    private List<GrantedAuthority> authorities(List<String> authorities) {
        return authorities
                .stream()
                .map(auth ->  new SimpleGrantedAuthority(auth))
                .collect(Collectors.toList());
    }
}
