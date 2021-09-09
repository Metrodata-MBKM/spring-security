package com.metrodatambkm.security.security;

import com.metrodatambkm.security.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    AuthenticationProvider provider;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

//    private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
//            new AntPathRequestMatcher("/api/**")
//    );

//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(provider);
//    }

//    @Override
//    public void configure(final WebSecurity webSecurity) {
//        webSecurity
//                .ignoring()
//                .antMatchers("/token/**")
//                .antMatchers("/api/**");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .exceptionHandling()
//                .and()
////                .authenticationProvider(provider)
//                .addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)
//                .authorizeRequests()
//                .requestMatchers(PROTECTED_URLS)
//                .authenticated()
//                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/v*/registration/**")
                .permitAll()
                .antMatchers("/login/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().disable()
                .httpBasic().disable()
                .logout().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

//    @Bean
//    AuthenticationFilter authenticationFilter() throws Exception {
//        final AuthenticationFilter filter = new AuthenticationFilter(PROTECTED_URLS);
//        filter.setAuthenticationManager(authenticationManager());
//        //filter.setAuthenticationSuccessHandler(successHandler());
//        return filter;
//    }
//
//    @Bean
//    AuthenticationEntryPoint forbiddenEntryPoint() {
//        return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
//    }
}
