package com.metrodatambkm.security.security;


import com.metrodatambkm.security.model.AppUserDetails;
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
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsernameOrEmail (usernameOrEmail, usernameOrEmail);
        System.out.println(user.getUsername());
        System.out.println("testing 1");
        System.out.println(user.getEmail());
        return new AppUserDetails(user);
    }
}
