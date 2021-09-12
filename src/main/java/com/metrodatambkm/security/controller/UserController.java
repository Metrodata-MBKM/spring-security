package com.metrodatambkm.security.controller;

import com.metrodatambkm.security.dto.UserDTO;
import com.metrodatambkm.security.service.BaseService;
import com.metrodatambkm.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasAnyRole('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('CREATE_USER')")
    public ResponseEntity<String> addUserSubmit(
            @RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
        return ResponseEntity.ok("Your Account Has Been Successfully Registered");
    }

}
