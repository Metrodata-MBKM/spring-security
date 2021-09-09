package com.metrodatambkm.security.controllers;

import com.metrodatambkm.security.dto.LoginRequest;
import com.metrodatambkm.security.dto.LoginResponse;
import com.metrodatambkm.security.services.LoginServiceImpl;
import com.metrodatambkm.security.services.impl.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @PostMapping("/token")
    public String getToken(@RequestBody LoginRequest loginRequest) {
        String token = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (StringUtils.isEmpty(token)) {
            return "no token found";
        }
        return token;
    }

    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return loginServiceImpl.loginToken(loginRequest);
    }
}
