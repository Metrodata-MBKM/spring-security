package com.metrodatambkm.security.controller;

import com.metrodatambkm.security.dto.UserDTO;
import com.metrodatambkm.security.model.RoleModel;
import com.metrodatambkm.security.model.UserModel;
import com.metrodatambkm.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public ResponseEntity<String> addUserSubmit(
            @RequestBody UserDTO userDTO){
        UserModel newUser = new UserModel();
        newUser.setEmail(userDTO.getEmail());
        newUser.setUsername(userDTO.getUsername());
        newUser.setPassword(userDTO.getPassword());
        userService.addUser(newUser);
        return ResponseEntity.ok("Your Account Has Been Successfully Registered");
    }

}
