package com.metrodatambkm.security.services;

import com.metrodatambkm.security.entities.AppUserDetails;
import com.metrodatambkm.security.entities.credentials.User;
import com.metrodatambkm.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    private EmailService emailService;

    @Autowired
    public AppUserDetailsService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    public UserDetails loadUserByUsername(String userIdentity) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmployee_Email(userIdentity, userIdentity);

        System.out.println("User loaded ! --------------");

        return new AppUserDetails(user);
    }

}
