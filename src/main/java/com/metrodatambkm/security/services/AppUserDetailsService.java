package com.metrodatambkm.security.services;

import com.metrodatambkm.security.config.PasswordEncoderConfig;
import com.metrodatambkm.security.entities.AppUserDetails;
import com.metrodatambkm.security.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoderConfig passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsernameOrByEmail(username);

        return new AppUserDetails(user);
    }


    private User findByUsernameOrByEmail(String parameter) {
        System.out.println(parameter);
        return allUser()
                .stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(parameter))
                .collect(Collectors.toList())
                .get(0);
    }

    private List<User> allUser() {
        List<User> users = new ArrayList<>();
        List<String> authUser1 = new ArrayList<>();
        List<String> authUser2 = new ArrayList<>();

        authUser1.add("role_admin");
        authUser2.add("role_operator");
        authUser2.add("create_data");

        User user1 = User.builder()
                .username("admin")
                .password(passwordEncoder.passwordEncoder().encode("admin"))
                .active(true)
                .authorities(authUser1)
                .build();

        User user2 = User.builder()
                .username("operator")
                .password(passwordEncoder.passwordEncoder().encode("operator"))
                .active(true)
                .authorities(authUser2)
                .build();

        users.add(user2);
        users.add(user1);

        return users;
    }
}
