package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dto.RegistrationRequest;
import com.metrodatambkm.security.models.AppUser;
import com.metrodatambkm.security.models.AppUserRole;
import com.metrodatambkm.security.models.ConfirmationToken;
import com.metrodatambkm.security.models.Role;
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
        boolean isValidEmail = emailValidator.
                test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = appUserService.signUpUser(
                new AppUser(
                        request.getUsername(),
                        request.getEmail(),
                        request.getPassword(),
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
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }

    private String buildEmail(String name, String link) {
        return "<a href=\"" + link + "\">Activate Now</a>";
    }
}
