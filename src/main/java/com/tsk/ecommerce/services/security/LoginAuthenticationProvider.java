package com.tsk.ecommerce.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginAuthenticationToken loginAuthenticationToken = (LoginAuthenticationToken) authentication;
        String username = (String) loginAuthenticationToken.getCredentials();
        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);
        if (passwordEncoder.matches(loginAuthenticationToken.getPassword(), customUserDetails.getPassword())) {
            return new LoginAuthenticationToken(customUserDetails.getUserEntity(), customUserDetails.getAuthorities());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (LoginAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
