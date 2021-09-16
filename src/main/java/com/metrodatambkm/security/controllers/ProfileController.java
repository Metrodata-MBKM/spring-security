package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dto.EmployeeRequestDTO;
import com.metrodatambkm.security.dto.ProfileResponse;
import com.metrodatambkm.security.models.credentials.AppUser;
import com.metrodatambkm.security.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private AppUserService userService;

    @GetMapping
    public ProfileResponse getProfile() {
        return userService.getProfile();
    }

    @PutMapping
    public AppUser updateProfile(@RequestBody EmployeeRequestDTO request) {
        return userService.updateProfile(request);
    }
}
