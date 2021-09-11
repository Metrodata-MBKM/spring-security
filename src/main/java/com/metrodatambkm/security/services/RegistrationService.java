package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dto.RegistrationRequest;
import com.metrodatambkm.security.models.credentials.AppUser;
import com.metrodatambkm.security.models.credentials.ConfirmationToken;
import com.metrodatambkm.security.models.Role;
import com.metrodatambkm.security.models.hr_schema.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class RegistrationService {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private EmailService emailService;


    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        AppUser appUser = new AppUser();
        Employee employee = new Employee();
        employee.setEmail(request.getEmail());
        appUser.setEmployee(employee);

        String token = appUserService.signUpUser(
                new AppUser(
                        request.getUsername(),
                        request.getPassword(),
                        new Employee(request.getEmail()),
                        new Role(2L)
                )
        );

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
        emailService.send(request.getEmail(), buildEmail(request.getUsername(), link));

        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(confirmationToken.getAppUser().getEmployee().getEmail());
        return "confirmed";
    }

    private String buildEmail(String name, String link) {
        return "<a href=\"" + link + "\">Activate Now</a>";
    }
}
