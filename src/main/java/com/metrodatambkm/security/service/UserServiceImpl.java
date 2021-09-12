package com.metrodatambkm.security.service;

import com.metrodatambkm.security.dto.UserDTO;
import com.metrodatambkm.security.model.RoleModel;
import com.metrodatambkm.security.model.UserModel;
import com.metrodatambkm.security.repository.RoleRepository;
import com.metrodatambkm.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserModel addUser(UserDTO user) {
        UserModel newUser = new UserModel();
        Set<RoleModel> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ROLE_"+user.getRole()));
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(encrypt(user.getPassword()));
        newUser.setRole(roles);
        return userRepository.save(newUser);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void updatePassword(UserModel user, String newPass) {
        UserModel userTarget = userRepository.findByUsername(user.getUsername());
        userTarget.setPassword(encrypt(newPass));
        userRepository.save(userTarget);
    }
}
