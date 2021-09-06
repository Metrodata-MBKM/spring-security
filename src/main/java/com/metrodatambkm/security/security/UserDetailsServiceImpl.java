package com.metrodatambkm.security.security;


import com.metrodatambkm.security.model.UserModel;
import com.metrodatambkm.security.model.RoleModel;
import com.metrodatambkm.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);
        Set<GrantedAuthority> grantedAuthorities = user.getRole().stream()
                .map(roleModel -> new SimpleGrantedAuthority(roleModel.getName()))
                .collect(Collectors.toSet());

        return  new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
