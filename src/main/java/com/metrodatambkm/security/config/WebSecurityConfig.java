package com.metrodatambkm.security.config;

import com.metrodatambkm.security.services.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private AppUserDetailsService appUserDetailsService;
    private PasswordEncoderConfig passwordEncoderConfig;

    @Autowired
    public WebSecurityConfig(AppUserDetailsService appUserDetailsService, PasswordEncoderConfig passwordEncoderConfig) {
        this.appUserDetailsService = appUserDetailsService;
        this.passwordEncoderConfig = passwordEncoderConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .disable()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(appUserDetailsService)
                .passwordEncoder(passwordEncoderConfig.passwordEncoder());
        System.out.println("USER SERVICE LOADED "+auth.inMemoryAuthentication().getUserDetailsService());
    }
}
