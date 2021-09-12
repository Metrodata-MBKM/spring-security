/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metrodatambkm.security.services;

import com.metrodatambkm.security.models.AppUserDetails;
import com.metrodatambkm.security.models.Role;
import com.metrodatambkm.security.models.User;
import com.metrodatambkm.security.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author gabri
 */
public class AppUserDetailsService implements UserDetailsService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    
    private User user;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String parameter) throws UsernameNotFoundException {
        User user=userRepository.findByUsernameOrEmployee_Email(parameter, parameter);
        return new AppUserDetails(user);
    }
    
//    private User findByUsernameOrByEmail(String parameter){
//        System.out.println(parameter);
//        return allUser()
//                .stream()
//                .filter(user->user.getUsername().equalsIgnoreCase(parameter))
//                .collect(Collectors.toList())
//                .get(0);
//    }
//    
//        private List<User> allUser(){
//            List<User> users=new ArrayList<>();
//            List<String> authUser1=new ArrayList<>();
//            List<String> authUser2=new ArrayList<>();
//
//            authUser1.add("role_admin");
//            authUser2.add("role_operator");
//            authUser2.add("create_data");
//
           
              
//            User user1=User.builder()
//                    .username("admin")
//                    .password(passwordEncoder.encode("admin"))
//                    .active(true)
//                    .authorities(authUser1)
//                    .build();
//
//            User user2=User.builder()
//                    .username("operator")
//                    .password(passwordEncoder.encode("operator"))
//                    .active(true)
//                    .authorities(authUser2)
//                    .build();
//
//            users.add(user2);
//            users.add(user1);
//
//            return users;
//
//    }
//    
//        
//    private List<GrantedAuthority> authorities(List<String> authorities) {
//        return authorities
//                .stream()
//                .map(auth ->  new SimpleGrantedAuthority(auth))
//                .collect(Collectors.toList());
//    }

//    @Override
//    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
//                User user=findByUsernameOrByEmail(parameter,parameter);       
//                         return new AppUserDetails(user);
//    }
    
}
