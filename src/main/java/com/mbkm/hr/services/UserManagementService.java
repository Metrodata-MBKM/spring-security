/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mbkm.hr.services;

import com.mbkm.hr.dtos.response.ConfirmationResponseDTO;
import com.mbkm.hr.dtos.request.LoginRequestDTO;
import com.mbkm.hr.dtos.response.LoginResponseDTO;
import com.mbkm.hr.dtos.request.RegisterRequestDTO;
import com.mbkm.hr.dtos.response.RegisterResponseDTO;
import com.mbkm.hr.events.OnRegistrationCompleteEvent;
import com.mbkm.hr.exceptions.AlreadyExistExceptions;
import com.mbkm.hr.exceptions.UnauthorizedExceptions;
import com.mbkm.hr.models.credentials.Privilege;
import com.mbkm.hr.models.credentials.Role;
import com.mbkm.hr.models.credentials.User;
import com.mbkm.hr.models.credentials.VerificationToken;
import com.mbkm.hr.models.hrschemas.Employee;
import com.mbkm.hr.repositories.AppUserRepository;
import com.mbkm.hr.repositories.DepartmentRepository;
import com.mbkm.hr.repositories.EmployeeRepository;
import com.mbkm.hr.repositories.JobRepository;
import com.mbkm.hr.repositories.RoleRepository;
import com.mbkm.hr.repositories.VerificationTokenRepository;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author hp
 */
@Service
public class UserManagementService {
    AppUserRepository appUserRepository;
    RoleRepository roleRepository;
    VerificationTokenRepository tokenRepository;
    PasswordEncoder encoder;
    ApplicationEventPublisher eventPublisher;
    EmployeeRepository employeeRepository;
    JobRepository jobRepository;
    DepartmentRepository departmentRepository;

    
    @Autowired
    public UserManagementService(AppUserRepository appUserRepository, RoleRepository roleRepository, VerificationTokenRepository tokenRepository, PasswordEncoder encoder, ApplicationEventPublisher eventPublisher, EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository) {
        this.appUserRepository = appUserRepository;
        this.roleRepository = roleRepository;
        this.tokenRepository = tokenRepository;
        this.encoder = encoder;
        this.eventPublisher = eventPublisher;
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
    }
    
    public RegisterResponseDTO register(RegisterRequestDTO request){
        if (appUserRepository.findByUsernameOrEmployee_Email(request.getUsername(), request.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username or Email Has Already Exist");
        } 
        
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("OPERATOR"));
        
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
                employeeRepository.getById(request.getManagerId())
        );
        
        employeeRepository.save(newemployee);
        
        User user = new User(
                null,
                request.getUsername(),
                encoder.encode(request.getPassword()),
                false,
                roles,
                newemployee);
        
        RegisterResponseDTO response = new RegisterResponseDTO()
                .generate(appUserRepository.save(user));

        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(response));

        return response;
        
    }

    
    
    public LoginResponseDTO login(LoginRequestDTO request){
        User user = appUserRepository.findByUsername(request.getUsername());
        
        System.out.println("result = "+user);
        if(user == null){
            throw new UnauthorizedExceptions("User not found!");
        }else if(!encoder.matches(request.getPassword(), user.getPassword())){
            throw new UnauthorizedExceptions("Your account has not been activated");
        }else if(user.isEnabled() == false){
            throw new UnauthorizedExceptions("Wrong credentials!");
        }else{
            Set<Role> userRole = user.getRoles();
            Set<String> autho = new HashSet<>();
            for (Role role : userRole) {
                autho.add("ROLE_"+role.getName().toUpperCase());
                for (Privilege privilege : role.getPrivileges()) {
                    autho.add(privilege.getName().toUpperCase());
                }
            }
            
            return new LoginResponseDTO(createLoginToken(request.getUsername(), 
                    request.getPassword()), autho);
        }
        
    }

    public String createLoginToken(String identity, String password){
        String auth = identity + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(
                auth.getBytes(Charset.forName("US-ASCII"))
        );

        String authHeader = new String(encodedAuth);
        return authHeader;
    }
    
    public void createVerificationToken(User user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    public ConfirmationResponseDTO confirmRegistration(String token){
        VerificationToken verificationToken = tokenRepository.findByToken(token);
        User user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();

        if(token == null){
            return new ConfirmationResponseDTO(false, "Token invalid");
        }

        if(verificationToken.getExpireDate().getTime() - cal.getTime().getTime() <= 0){
            return new ConfirmationResponseDTO(false, "Token Expired");
        }

        user.setEnabled(true);
        appUserRepository.save(user);
        return new ConfirmationResponseDTO(true, "Account Activated");
    }
}
