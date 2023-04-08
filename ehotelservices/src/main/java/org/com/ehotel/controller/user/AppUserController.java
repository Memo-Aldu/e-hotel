package org.com.ehotel.controller.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.user.AppUserDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.JwtAuthenticationService;
import org.com.ehotel.service.user.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

/**
 * @author : memo-aldu
 * @mailto : maldu064@uOttawa.ca
 * @created : 3/15/2023, Wednesday
 **/
@Slf4j @AllArgsConstructor @RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(
        allowCredentials = "true",
        origins = "http://localhost:3030, http://localhost:8080",
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
)
public class AppUserController {
    private final UserService userService;
    private final JwtAuthenticationService authService;
    private ResponseHandler responseHandler;

    /**
     * Get user by email
     * @param  request HttpServletRequest
     * @return ResponseEntity<AppHttpResponse>
     */
    @GetMapping
    public ResponseEntity<AppHttpResponse> getUser(HttpServletRequest request) {
        // getting the token from the request header
        final String token = authService.getTokenFromRequestHeader(request.getHeader(AUTHORIZATION));
        final String subject = authService.getSubjectFromToken(token);
        log.info("Getting user with email: " + subject);
        final AppUserDTO user = userService.getUserByEmail(subject);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .message("User fetched successfully")
                        .data(Map.of("user", user))
                        .build(),
                setupResponseHeaders(request));
    }

    /**
     * Register user
     * @param appUserDTO AppUserDTO
     * @param request HttpServletRequest
     * @return ResponseEntity<AppHttpResponse>
     */
    @PostMapping("/register")
    public ResponseEntity<AppHttpResponse> registerUser(
            @RequestBody AppUserDTO appUserDTO, HttpServletRequest request) {
        log.info("Registering user: " + appUserDTO);
        // Validate registration request
        if (appUserDTO == null) {
            throw new BadRequestException("Invalid registration request");
        }
        AppUserDTO registeredUser = userService.registerUser(appUserDTO);
        Map<String, Object> data = Map.of
                ("user", registeredUser,
                authService.getAccessTokenName(), authService.createToken(registeredUser),
                authService.getRefreshTokenName(), authService.createRefreshToken(registeredUser));
        log.info("User registered: " + registeredUser);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.CREATED)
                        .message("User registered successfully")
                        .data(data)
                        .build(),
                setupResponseHeaders(request));
    }

    /**
     * Update user
     * @param appUserDTO AppUserDTO
     * @param request HttpServletRequest
     * @return ResponseEntity<AppHttpResponse>
     */
    @PatchMapping
    public ResponseEntity<AppHttpResponse> updateUser(@RequestBody AppUserDTO appUserDTO, HttpServletRequest request) {
        log.info("Updating user with request: " + appUserDTO);
        // get token from header
        final String token = authService.getTokenFromRequestHeader(request.getHeader(AUTHORIZATION));
        // get subject from token
        final String subject = authService.getSubjectFromToken(token);
        final AppUserDTO updatedUser = userService.updateUser(subject, appUserDTO);

        Map<String, Object> data = Map.of
                ("user", updatedUser,
                        // create new token and refresh token
                        authService.getAccessTokenName(), authService.createToken(updatedUser),
                        authService.getRefreshTokenName(), authService.createRefreshToken(updatedUser));
        log.info("User updated: " + updatedUser);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.CREATED)
                        .message("User updated successfully")
                        .data(data)
                        .build(),
                setupResponseHeaders(request));

    }

    /**
     * Delete user
     * @param request HttpServletRequest
     * @return ResponseEntity<AppHttpResponse>
     */
    @DeleteMapping
    public ResponseEntity<AppHttpResponse> deleteUser(HttpServletRequest request) {
        // get token from header
        final String token = authService.getTokenFromRequestHeader(request.getHeader(AUTHORIZATION));
        // get subject from token
        final String subject = authService.getSubjectFromToken(token);
        userService.deleteUser(subject);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .message("User deleted successfully")
                        .build(),
                setupResponseHeaders(request));
    }

    private HttpHeaders setupResponseHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, "application/json");
        headers.set(AUTHORIZATION, request.getHeader(AUTHORIZATION));
        return headers;
    }
}
