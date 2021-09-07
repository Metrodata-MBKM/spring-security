package com.metrodatambkm.security.controller;

import com.metrodatambkm.security.dto.UserRequest;
import com.metrodatambkm.security.entity.Role;
import com.metrodatambkm.security.entity.User;
import com.metrodatambkm.security.service.AppUserDetailsService;
import com.metrodatambkm.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    private User register(@RequestBody UserRequest userRequest) {
        User user = new User(userRequest.getUsername(), userRequest.getEmail(), userRequest.getPassword());
        user.setRoles(Collections.singletonList(new Role(2L)));
        return userService.save(user);
    }

}
