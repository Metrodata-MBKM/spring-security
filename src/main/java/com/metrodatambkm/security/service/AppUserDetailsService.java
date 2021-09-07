package com.metrodatambkm.security.service;

import com.metrodatambkm.security.configuration.PasswordEncoderConfig;
import com.metrodatambkm.security.entity.AppUserDetails;
import com.metrodatambkm.security.entity.User;
import com.metrodatambkm.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoderConfig passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new AppUserDetails(user);
    }
}
