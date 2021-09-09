package com.metrodatambkm.security.services;

import com.metrodatambkm.security.dto.LoginRequest;
import com.metrodatambkm.security.dto.LoginResponse;
import com.metrodatambkm.security.models.AppUser;
import com.metrodatambkm.security.models.ConfirmationToken;
import com.metrodatambkm.security.repository.AppUserRepository;
import com.metrodatambkm.security.repository.ConfirmationTokenRepository;
import com.metrodatambkm.security.services.impl.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository tokenRepository;

    public String login(String username, String password) {
        AppUser user = userRepository.login(username, password);
        if (user != null) {
            Optional<ConfirmationToken> confirmationToken = tokenRepository.findByAppUser(user);
            if (confirmationToken.isPresent()) {
                return confirmationToken.get().getToken();
            }
        }
        return StringUtils.EMPTY;
    }

    public Optional<User> findByToken(String token) {
        Optional<ConfirmationToken> customer= tokenRepository.findByToken(token);
        if(customer.isPresent()){
            ConfirmationToken confirmationToken = customer.get();
            User user= new User(confirmationToken.getAppUser().getUsername(), confirmationToken.getAppUser().getPassword(), true, true, true, true,
                    AuthorityUtils.createAuthorityList("USER"));
            return Optional.of(user);
        }
        return  Optional.empty();
    }

    public LoginResponse loginToken(LoginRequest loginRequest) {
        AppUser appUser = userRepository.findByUsername(loginRequest.getUsername());

        return new LoginResponse(getToken(loginRequest.getUsername(), loginRequest.getPassword()), appUser.getAppUserRole());
    }

    private String getToken(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.getEncoder().encode(
                auth.getBytes(Charset.forName("US-ASCII"))
        );
        return new String(encodedAuth);
    }
}
