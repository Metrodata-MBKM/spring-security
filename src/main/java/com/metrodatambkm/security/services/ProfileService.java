package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dtos.request.ProfileRequest;
import com.metrodatambkm.security.dtos.response.ProfileResponse;
import com.metrodatambkm.security.entities.Employee;
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
        User user = userRepository.findByUsernameOrEmployee_Email(name, name);

        if(repository.findByUser(user) == null){
            throw new ResourceNotFoundException("Setup your employee first");
        }

        return new ProfileResponse(repository.findByUser(user));
    }

    public ProfileResponse save(ProfileRequest request, String name){
        User user = userRepository.findByUsernameOrEmployee_Email(name, name);
        Employee employee = new Employee();

        if(repository.findByUser(user) == null){
            employee = new Employee(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getPhone(),
                    request.getBirthDate(),
                    user);
        }else{
            employee = repository.findByUser(user);
            employee.setFirstName(request.getFirstName());
            employee.setLastName(request.getLastName());
            employee.setHireDate(request.getBirthDate());
            employee.setPhone(request.getPhone());
            employee.setUser(user);
        }

        return new ProfileResponse(repository.save(employee));

    }
}
