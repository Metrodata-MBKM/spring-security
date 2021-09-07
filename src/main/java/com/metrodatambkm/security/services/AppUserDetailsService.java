package com.metrodatambkm.security.services;

import com.metrodatambkm.security.config.PasswordEncoderConfig;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private PasswordEncoderConfig passwordEncoder;

    @Autowired
    public AppUserDetailsService(PasswordEncoderConfig passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Username =" + username);

        List<UserDetails> users = new ArrayList<>();
        List<String> authoritiesUser1 = new ArrayList<>();

        authoritiesUser1.add("ROLE_OPERATOR");
        authoritiesUser1.add("CREATE_REGION");

        UserDetails user1 = User.builder()
                .username("operator")
                .password(passwordEncoder.passwordEncoder().encode("operator"))
                .authorities("ROLE_OPERATOR")
                .build();

        UserDetails user2 = User.builder()
                .username("admin")
                .password(passwordEncoder.passwordEncoder().encode("admin"))
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
