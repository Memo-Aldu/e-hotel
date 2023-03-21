package org.com.ehotel.controller.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.requests.RegistrationRequest;
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
public class AppUserController {
    private final UserService userService;
    private final JwtAuthenticationService authService;
    private ResponseHandler responseHandler;

    /**
     * Get user by email
     * @param email user email
     * @param  request HttpServletRequest
     * @return ResponseEntity<AppHttpResponse>
     */
    @GetMapping("/{email}")
    public ResponseEntity<AppHttpResponse> getUserByEmail(
            @PathVariable String email, HttpServletRequest request) {
        log.info("Getting user with email: " + email);
        AppUserDTO user = userService.getUserByEmail(email);
        log.info("User found: " + user);
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
     * @param regRequest RegistrationRequest
     * @param request HttpServletRequest
     * @return ResponseEntity<AppHttpResponse>
     */
    @PostMapping("/register")
    public ResponseEntity<AppHttpResponse> registerUser(
            @RequestBody RegistrationRequest regRequest, HttpServletRequest request) {
        log.info("Registering user: " + regRequest);
        // Validate registration request
        if (!regRequest.isValid()) {
            throw new BadRequestException("Invalid registration request");
        }
        AppUserDTO registeredUser = userService.registerUser(regRequest);
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

    //TODO: UPDATE USER
    //TODO: DELETE USER

    private HttpHeaders setupResponseHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, "application/json");
        headers.set(AUTHORIZATION, request.getHeader(AUTHORIZATION));
        return headers;
    }
}
