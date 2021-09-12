package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dto.EmployeeRequest;
import com.metrodatambkm.security.dto.ProfileResponse;
import com.metrodatambkm.security.models.credentials.AppUser;
import com.metrodatambkm.security.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private AppUserService userService;

    @GetMapping
    public ProfileResponse getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getProfile(username);
    }

    @PutMapping
    public AppUser updateProfile(@RequestBody EmployeeRequest request) {
        return userService.updateProfile(request);
    }
}
