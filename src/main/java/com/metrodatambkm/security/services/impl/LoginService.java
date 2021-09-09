package com.metrodatambkm.security.services.impl;

import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface LoginService {
    String login(String username, String password);
    Optional<User> findByToken(String token);
}
