package org.com.ehotel.service;

import org.com.ehotel.dto.auth.AuthenticationRequestDTO;
import org.com.ehotel.dto.user.AppUserDTO;
import org.com.ehotel.enums.AppRoles;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface JwtAuthenticationService {
    Map<String, Object> createTokenWitheRefreshToken(String refreshToken);
    boolean authenticateToken(String token);
    Authentication authenticateLoginRequest(AuthenticationRequestDTO authRequest);
    String createToken(AppUserDTO user);
    String createToken(String subject, AppRoles role);
    String createRefreshToken(AppUserDTO user);
    String createRefreshToken(String subject);
    String getAccessTokenName();
    String getRefreshTokenName();
    String getSubjectFromToken(String token);
    String getTokenFromRequestHeader(String header);
}
