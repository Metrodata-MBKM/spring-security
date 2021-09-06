package com.metrodatambkm.security.controller;

import com.metrodatambkm.security.dto.UserDTO;
import com.metrodatambkm.security.model.UserModel;
import com.metrodatambkm.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping("/login")
    public String login(){
        return "login";
    }


//    @PostMapping("/addUser")
//    public ResponseEntity<String> addUserSubmit(
//            @RequestBody UserDTO userDTO){
//        UserModel newUser = new UserModel();
//        newUser.setEmail(userDTO.getEmail());
//        newUser.setUsername(userDTO.getEmail());
//        newUser.setPassword(userDTO.getPassword());
//        userRepository.save(newUser);
//        return ResponseEntity.ok("Your Account Has Been Successfully Registered");
//    }
}
