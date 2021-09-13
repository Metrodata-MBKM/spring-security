package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dto.EmployeeRequest;
import com.metrodatambkm.security.dto.ProfileResponse;
import com.metrodatambkm.security.models.credentials.AppUser;
import com.metrodatambkm.security.models.credentials.ConfirmationToken;
import com.metrodatambkm.security.models.hr_schema.Employee;
import com.metrodatambkm.security.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class AppUserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "user with username %s not found";

    private AppUserRepository appUserRepository;
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, ConfirmationTokenService confirmationTokenService) {
        this.appUserRepository = appUserRepository;
        this.confirmationTokenService = confirmationTokenService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        return appUserRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format(USER_NOT_FOUND_MSG, username)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByUsername(appUser.getUsername()).isPresent();

        if (userExists) {
            throw new IllegalStateException("user is already exists");
        }

        String encodedPassword = encoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public ProfileResponse getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        AppUser appUser = appUserRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));

        return new ProfileResponse(
                appUser.getEmployee().getFirstName() + " " + appUser.getEmployee().getLastName(),
                appUser.getEmployee().getEmail(),
                appUser.getEmployee().getPhoneNumber()
        );
    }

    public AppUser updateProfile(EmployeeRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        AppUser appUser = appUserRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found"));
        Employee employee = new Employee(
                request.getFirstName(),
                request.getLastName(),
                request.getPhoneNumber(),
                request.getDepartment(),
                request.getJob(),
                request.getManager()
        );
        appUser.setEmployee(employee);
        return appUserRepository.save(appUser);
    }
}
