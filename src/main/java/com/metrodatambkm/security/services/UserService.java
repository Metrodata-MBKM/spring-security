package com.metrodatambkm.security.services;

import com.metrodatambkm.security.entities.credentials.User;
import com.metrodatambkm.security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getById(Integer id){
        return userRepository.getById(id);
    }

}
