package com.metrodatambkm.security.config;

import com.metrodatambkm.security.services.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
    private PasswordEncoderConfig passwordEncoder;

    @Autowired
    public WebSecurityConfig(AppUserDetailsService appUserDetailsService,
                             PasswordEncoderConfig passwordEncoder) {
        this.appUserDetailsService = appUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(appUserDetailsService)
                .passwordEncoder(passwordEncoder.passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/region").hasAuthority("CREATE_REGION")
                .antMatchers("/region/**").hasAnyRole("ADMIN","OPERATOR")
                .antMatchers("/employee/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .disable()
                .httpBasic();
    }
}
