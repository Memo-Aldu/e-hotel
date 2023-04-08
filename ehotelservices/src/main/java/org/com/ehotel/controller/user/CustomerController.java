package org.com.ehotel.controller.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.user.CustomerDTO;
import org.com.ehotel.enums.AppRoles;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.JwtAuthenticationService;
import org.com.ehotel.service.user.CustomerService;
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
 * @created : 4/5/2023, Wednesday
 **/
@RestController @Slf4j
@AllArgsConstructor @RequestMapping("/api/v1/customer")
@CrossOrigin(
        allowCredentials = "true",
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT}
)
// This Controller uses the jwt subject to identify the customer
public class CustomerController {
    private final CustomerService customerService;
    private final JwtAuthenticationService authService;
    private final ResponseHandler responseHandler;

    @GetMapping
    public ResponseEntity<AppHttpResponse> findCustomerByEmail(HttpServletRequest request) {
        String subject = getSubjectFromHttpRequest(request);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "customer", customerService.findCustomerByEmail(subject),
                                "email", subject))
                        .message("Customer found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PostMapping
    public ResponseEntity<AppHttpResponse> createCustomer
            (@RequestBody CustomerDTO customer, HttpServletRequest request) {
        if(customer == null) {
            throw new BadRequestException("Customer is required");
        }
        CustomerDTO customerDTO = customerService.save(customer);
        Map<String, Object> data = Map.of
                ("customer", customerDTO,
                        authService.getAccessTokenName(), authService.createToken(customerDTO.email(), AppRoles.ROLE_CUSTOMER),
                        authService.getRefreshTokenName(), authService.createRefreshToken(customerDTO.email())
                );
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(data)
                        .message("Customer created")
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PatchMapping
    public ResponseEntity<AppHttpResponse> updateCustomer(
            @RequestBody CustomerDTO updatedCustomer, HttpServletRequest request) {
        if(updatedCustomer == null) {
            throw new BadRequestException("Invalid customer");
        }
        String subject = getSubjectFromHttpRequest(request);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "customer", customerService.update(updatedCustomer, subject)))
                        .message("Customer updated")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @DeleteMapping
    public ResponseEntity<AppHttpResponse> deleteCustomerByEmail(HttpServletRequest request) {
        String subject = getSubjectFromHttpRequest(request);
        customerService.deleteByEmail(subject);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("email", subject))
                        .message("Customer deleted")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    private HttpHeaders setupResponseHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, "application/json");
        headers.set(AUTHORIZATION, request.getHeader(AUTHORIZATION));
        return headers;
    }

    private String getSubjectFromHttpRequest(HttpServletRequest request) {
        final String token = authService.getTokenFromRequestHeader(request.getHeader(AUTHORIZATION));
        final String subject = authService.getSubjectFromToken(token);
        log.info("Getting user with email: " + subject);
        return subject;
    }
}
