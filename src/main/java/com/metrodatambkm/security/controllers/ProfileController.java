package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.request.ProfileRequest;
import com.metrodatambkm.security.dtos.response.ProfileResponse;
import com.metrodatambkm.security.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@PreAuthorize("isAuthenticated()")
public class ProfileController {

    @Autowired
    ProfileService service;

    @PostMapping
    public ProfileResponse save(@RequestBody ProfileRequest request, Authentication authentication){
        return service.save(request, authentication.getName());
    }

    @GetMapping
    public ProfileResponse getCurrentProfile(Authentication authentication){
        return service.getCurrentProfile(authentication.getName());
    }
}
