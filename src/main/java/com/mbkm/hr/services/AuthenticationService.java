/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.dto.AuthRequestDTO;
import com.mbkm.hr.dto.AuthResponseDTO;
import com.mbkm.hr.dto.ConfirmationResponse;
import com.mbkm.hr.dto.RegisterRequest;
import com.mbkm.hr.dto.RegisterResponse;
import com.mbkm.hr.events.OnRegistrationCompleteEvent;
import com.mbkm.hr.models.Employee;
import com.mbkm.hr.models.Privilege;
import com.mbkm.hr.models.Role;
import com.mbkm.hr.models.User;
import com.mbkm.hr.models.VerificationToken;
import com.mbkm.hr.repositories.DepartmentRepository;
import com.mbkm.hr.repositories.EmployeeRepository;
import com.mbkm.hr.repositories.JobRepository;
import com.mbkm.hr.repositories.RoleRepository;
import com.mbkm.hr.repositories.UserRepository;
import com.mbkm.hr.repositories.VerificationTokenRepository;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author loisceka
 */
@Service
public class AuthenticationService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    EmployeeRepository employeeRepository;
    VerificationTokenRepository tokenRepository;
    PasswordEncoder encoder;
    ApplicationEventPublisher eventPublisher;
    JobRepository jobRepository;
    DepartmentRepository departmentRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository, EmployeeRepository employeeRepository, VerificationTokenRepository tokenRepository, PasswordEncoder encoder, ApplicationEventPublisher eventPublisher, JobRepository jobRepository, DepartmentRepository departmentRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
        this.tokenRepository = tokenRepository;
        this.encoder = encoder;
        this.eventPublisher = eventPublisher;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
    }

    public String CreateLoginToken(String identity, String password) {
        String auth = identity + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(
                auth.getBytes(Charset.forName("US-ASCII"))
        );

        String authHeader = new String(encodedAuth);
        return authHeader;
    }

    public AuthResponseDTO login(AuthRequestDTO request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!");
        }
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password!");
        }
        if (!user.isEnabled()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Your account has not been activated");
        }

        return new AuthResponseDTO(CreateLoginToken(request.getUsername(), request.getPassword()), generateAuthorities(user.getRoles()));
    }

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.findByUsernameOrEmployee_Email(request.getUsername(), request.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username or Email Has Already Exist");
        }
        Set<Role> roles = new HashSet<>();
        // GIVING DEFAULT ROLE
        roles.add(roleRepository.findByName("EMPLOYEE"));

        Employee newemployee = new Employee(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPhoneNumber(),
                request.getHireDate(),
                request.getSalary(),
                request.getCommissionPct(),
                jobRepository.getById(request.getJob()),
                departmentRepository.getById(request.getDepartment()),
                employeeRepository.getById(request.getManager())
        );

        User user = new User(
                null,
                request.getUsername(),
                encoder.encode(request.getPassword()),
                false,
                roles,
                newemployee
        );

        RegisterResponse response = new RegisterResponse().generate(userRepository.save(user));
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(response));

        return response;
    }

    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    public ConfirmationResponse confirmRegistration(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if (token == null) {
            return new ConfirmationResponse(false, "Token invalid");
        }

        if (verificationToken.getExpireDate().getTime() - cal.getTime().getTime() <= 0) {
            return new ConfirmationResponse(false, "Token Expired");
        }

        user.setEnabled(true);
        tokenRepository.delete(verificationToken);
        userRepository.save(user);
        return new ConfirmationResponse(true, "Account Activated");
    }

    private List<String> generateAuthorities(Set<Role> roles) {
        List<String> authorities = new ArrayList<>();

        for (Role role : roles) {
            authorities.add("ROLE_" + role.getName().toUpperCase());
            for (Privilege privilege : role.getPrivileges()) {
                authorities.add(privilege.getName());
            }
        }

        return authorities;
    }

}
