package org.com.ehotel.controller.user;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.auth.AuthenticationRequestDTO;
import org.com.ehotel.dto.user.AppUserDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.exceptions.MissingAuthenticationException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.configuration.security.JWTUtils;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.JwtAuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 2023-03-21, Tuesday
 **/
@RestController @Slf4j @CrossOrigin("*")
@AllArgsConstructor @RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private ResponseHandler responseHandler;
    private final JwtAuthenticationService authService;
    private final JWTUtils jwtUtils;

    /**
     * Login the user
     * @param authenticationRequest AuthenticationRequestDTO
     * @param request HttpServletRequest
     * @return ResponseEntity<AppHttpResponse>
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AppHttpResponse> authenticate(@RequestBody AuthenticationRequestDTO authenticationRequest,
                                                        HttpServletRequest request) {
        if (authenticationRequest.isValid()) {
            log.info("Authenticating user: " + authenticationRequest);
            Authentication auth = authService.authenticateLoginRequest(authenticationRequest);
            if(auth == null || !auth.isAuthenticated()) {
                throw new BadRequestException("Invalid credentials");
            }
            AppUserDTO user = (AppUserDTO) auth.getPrincipal();
            final String token = authService.createToken(user);
            final String refreshToken = authService.createRefreshToken(user);
            Map<String, Object> data = Map.of(
                    jwtUtils.jwtConfig.accessHeader(), token,
                    jwtUtils.jwtConfig.refreshHeader(), refreshToken,
                    "user", user);
            return responseHandler.httpResponse(
                    AppHttpResponse.builder()
                            .success(true)
                            .message("User authenticated successfully")
                            .data(data)
                            .timestamp(LocalDateTime.now())
                            .status(HttpStatus.OK)
                            .build(), setupResponseHeaders(request));
        }
        else{
            log.info("Invalid authentication request: " + authenticationRequest);
            throw new BadRequestException("Invalid request");
        }
    }

    /**
     * Refresh the token
     * @param request HttpServletRequest
     * @return ResponseEntity<AppHttpResponse>
     */
    @PostMapping("/token/refresh")
    private ResponseEntity<AppHttpResponse> refreshToken(@NonNull HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && jwtUtils.tokenStartsWithPrefix(authorizationHeader)) {
            try {
                String refreshToken = jwtUtils.getTokenFromHeader(authorizationHeader);
                return responseHandler.httpResponse(
                        AppHttpResponse.builder()
                                .message("Token refreshed successfully")
                                .data(authService
                                        .createTokenWitheRefreshToken(refreshToken))
                                .success(true)
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.OK)
                                .build(),
                        setupResponseHeaders(request));
            } catch (Exception e) {
                log.error("Error logging in : {}", e.getMessage());
                return responseHandler.httpResponse(
                        AppHttpResponse.builder()
                                .message("Error refreshing token")
                                .success(false)
                                .timestamp(LocalDateTime.now())
                                .status(HttpStatus.BAD_REQUEST)
                                .build(),
                        setupResponseHeaders(request));
            }
        }
        else {
            throw new MissingAuthenticationException("Authorization header must be provided");
        }
    }

    private HttpHeaders setupResponseHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, "application/json");
        headers.set(AUTHORIZATION, request.getHeader(AUTHORIZATION));
        return headers;
    }
}
