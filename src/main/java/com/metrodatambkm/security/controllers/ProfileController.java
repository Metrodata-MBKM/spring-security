package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dtos.ProfileRequest;
import com.metrodatambkm.security.dtos.ProfileResponse;
import com.metrodatambkm.security.entities.Profile;
import com.metrodatambkm.security.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
@PreAuthorize("hasAnyRole('ADMIN', 'OPERATOR')")
public class ProfileController {

    @Autowired
    ProfileService service;

    @PostMapping
    public ProfileResponse save(@RequestBody ProfileRequest request, Principal principal){
        return service.save(request, principal);
    }

    @GetMapping
    public ProfileResponse getCurrentProfile(Principal principal){
        return service.getCurrentProfile(principal);
    }
}
