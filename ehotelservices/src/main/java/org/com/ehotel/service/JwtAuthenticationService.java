package org.com.ehotel.service;

import org.com.ehotel.dto.requests.AuthenticationRequestDTO;
import org.com.ehotel.dto.user.AppUserDTO;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface JwtAuthenticationService {
    Map<String, Object> createTokenWitheRefreshToken(String refreshToken);
    boolean authenticateToken(String token);
    Authentication authenticateLoginRequest(AuthenticationRequestDTO authRequest);
    String createToken(AppUserDTO user);
    String createRefreshToken(AppUserDTO user);
    String getAccessTokenName();
    String getRefreshTokenName();
}
