package com.example.eBookingAppointment.service;

import com.example.eBookingAppointment.user.User;
import com.example.eBookingAppointment.user.UserRole;
import com.example.eBookingAppointment.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomAuthenticationProviderService implements AuthenticationProvider {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        UserDetails user = userService.loadUserByUsername(email);
        if (user != null) {
            if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
                return new UsernamePasswordAuthenticationToken(email, password, user.getAuthorities());
            }
        }
        throw new BadCredentialsException("invalid credentials");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
