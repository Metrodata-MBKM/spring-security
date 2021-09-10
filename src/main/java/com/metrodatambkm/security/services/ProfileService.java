package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dtos.ProfileRequest;
import com.metrodatambkm.security.dtos.ProfileResponse;
import com.metrodatambkm.security.entities.Profile;
import com.metrodatambkm.security.entities.credentials.User;
import com.metrodatambkm.security.exceptions.ResourceNotFoundException;
import com.metrodatambkm.security.repositories.ProfileRepository;
import com.metrodatambkm.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    ProfileRepository repository;

    @Autowired
    UserRepository userRepository;


    public ProfileResponse getCurrentProfile(String name){
        User user = userRepository.findByUsernameOrEmail(name, name);

        if(repository.findByUser(user) == null){
            throw new ResourceNotFoundException("Setup your profile first");
        }

        return new ProfileResponse(repository.findByUser(user));
    }

    public ProfileResponse save(ProfileRequest request, String name){
        User user = userRepository.findByUsernameOrEmail(name, name);
        Profile profile = new Profile();

        if(repository.findByUser(user) == null){
            profile = new Profile(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getPhone(),
                    request.getBirthDate(),
                    user);
        }else{
            profile = repository.findByUser(user);
            profile.setFirstName(request.getFirstName());
            profile.setLastName(request.getLastName());
            profile.setBirthDate(request.getBirthDate());
            profile.setPhone(request.getPhone());
            profile.setUser(user);
        }

        return new ProfileResponse(repository.save(profile));

    }
}
