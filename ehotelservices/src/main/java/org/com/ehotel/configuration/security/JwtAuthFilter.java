package org.com.ehotel.configuration.security;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.configuration.EndpointConfig;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 *@author :memo-aldu
 *@mailto :maldu064@uOttawa.ca
 *@created :2023-03-21,Tuesday
 **/

@AllArgsConstructor @Component @Slf4j
public class JwtAuthFilter extends OncePerRequestFilter { 
   private final EndpointConfig endpointConfig;
   private final JWTUtils jwtUtils;
   private ResponseHandler responseHandler;
   @Override
   protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                   @NonNull FilterChain filterChain) throws ServletException, IOException {
       // check if the requested endpoint is open
       log.info("Requested endpoint : {}", request.getServletPath());
       if (!endpointConfig.isSecured.test(request)) {
           log.info("Open endpoint : {}", request.getServletPath());
           // let the request pass
           filterChain.doFilter(request, response);
       } else {
           // get the authorization header
           final String authorizationHeader = request.getHeader(AUTHORIZATION);
           response.setHeader(AUTHORIZATION, authorizationHeader);
           // check if the authorization header is not null and starts with Bearer
           if (authorizationHeader != null && jwtUtils.tokenStartsWithPrefix(authorizationHeader)) {
               try {
                   // authenticate the token
                   Authentication authentication = jwtUtils.authenticateToken(
                           jwtUtils.getTokenFromHeader(authorizationHeader));
                   SecurityContextHolder.getContext().setAuthentication(authentication);
                   filterChain.doFilter(request, response);
               } catch (Exception e) {
                   // if the token is invalid, return an error
                   response = responseHandler.jsonResponse(
                           AppHttpResponse.builder()
                                   .data(Map.of("error", "Invalid token"))
                                   .timestamp(LocalDateTime.now())
                                   .status(HttpStatus.UNAUTHORIZED)
                                   .success(false)
                                   .message(e.getMessage())
                                   .build(), response);
               }
           } else {
               // if the authorization header is null, return an error
               response = responseHandler.jsonResponse(
                       AppHttpResponse.builder()
                               .timestamp(LocalDateTime.now())
                               .data(Map.of("error", "Missing " +
                                       "authentication header"))
                               .message("Missing authorization header")
                               .status(HttpStatus.BAD_REQUEST)
                               .success(false)
                               .data(new HashMap<>())
                               .build(), response);
           }
       }
   }
}
