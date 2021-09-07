/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.models.AppUserDetails;
import com.mbkm.hr.models.User;
import com.mbkm.hr.repositories.AppUserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author WahyuKu
 */
@Service
public class AppUserDetailsService implements UserDetailsService {
    
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
        User user = appUserRepository.findByUsername(username);
        
        return new AppUserDetails(user);
    }
    
//    private User findByUsernameOrByEmail(String parameter) {
//        System.out.println(parameter);
//        return allUser()
//                .stream()
//                .filter(user -> user.getUsername().equalsIgnoreCase(parameter))
//                .collect(Collectors.toList())
//                .get(0);
//    }
    
//    private List<User> allUser() {
//        List<User> users = new ArrayList<>();
//        List<String> authUser1 = new ArrayList<>();
//        List<String> authUser2 = new ArrayList<>();
//        
//        authUser1.add("role_admin");
//        authUser2.add("role_operator");
//        authUser2.add("create_data");
//        
//        User user1 = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin"))
//                .active(true)
//                .authorities(authUser1)
//                .build();
//        
//        User user2 = User.builder()
//                .username("operator")
//                .password(passwordEncoder.encode("operator"))
//                .active(true)
//                .authorities(authUser2)
//                .build();
//                
//       users.add(user2);
//       users.add(user1);
//       
//       return users;
//    }

    public AppUserDetailsService() {
    }
}
