package com.bagaggio.user_management.service;

import com.bagaggio.user_management.dto.login.LoginDTO;
import com.bagaggio.user_management.dto.login.LoginResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtService jwtService;

    public LoginResponseDTO login(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());
        final String jwt = jwtService.generateToken(userDetails);

        return new LoginResponseDTO(jwt);
    }
}
