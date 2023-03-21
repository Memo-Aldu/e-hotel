package org.com.ehotel.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.configuration.security.JWTConfig;
import org.com.ehotel.dto.requests.AuthenticationRequestDTO;
import org.com.ehotel.configuration.security.JWTUtils;
import org.com.ehotel.dto.user.AppUserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service @Slf4j @AllArgsConstructor
public class JwtAuthenticationServiceImp implements JwtAuthenticationService {
    private final JWTUtils jwtUtils;
    private final JWTConfig jwtConfig;

    private AuthenticationManager authenticationManager;

    @Override
    public Map<String, Object> createTokenWitheRefreshToken(String refreshToken) {
        String newToken = jwtUtils.createTokenWithRefreshToken(refreshToken);
        Map<String, Object> responseBody = new HashMap<>();
        log.info("New token is : {}", newToken);
        responseBody.put(jwtConfig.accessHeader(), newToken);
        responseBody.put(jwtConfig.refreshHeader(), refreshToken);
        return responseBody;
    }

    @Override
    public Authentication authenticateLoginRequest(AuthenticationRequestDTO authRequest) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.email(), authRequest.password()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("User {} is authenticated : {} ",
                authentication.getName(), authentication.isAuthenticated());
        return authenticationManager.authenticate(authentication);
    }

    @Override
    public String createToken(AppUserDTO user) {
        return jwtUtils.createToken(user);
    }

    @Override
    public String createRefreshToken(AppUserDTO user) {
        return jwtUtils.createRefreshToken(user);
    }

    @Override
    public String getAccessTokenName() {
        return jwtConfig.accessHeader();
    }

    @Override
    public String getRefreshTokenName() {
        return jwtConfig.refreshHeader();
    }

    @Override
    public boolean authenticateToken(String authorizationHeader) {
        Authentication authentication = jwtUtils.authenticateToken(authorizationHeader);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("User {} is authenticated : {} ",
                authentication.getName(), authentication.isAuthenticated());
        return authentication.isAuthenticated();
    }
}
