package org.com.ehotel.controller.user;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.user.EmployeeDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.JwtAuthenticationService;
import org.com.ehotel.service.user.EmployeeService;
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
 * @created : 4/6/2023, Thursday
 **/
@RestController @AllArgsConstructor @CrossOrigin("*")
@RequestMapping("/api/v1/employee") @Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;
    private final ResponseHandler responseHandler;
    private final JwtAuthenticationService authService;

    @GetMapping
    public ResponseEntity<AppHttpResponse> getCustomer(HttpServletRequest request) {
        String subject = getSubjectFromHttpRequest(request);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "employee", employeeService.findEmployeeByEmail(subject),
                                "email", subject))
                        .message("Employee found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PostMapping
    public ResponseEntity<AppHttpResponse> createEmployee(
            @RequestBody EmployeeDTO employeeDTO, HttpServletRequest request) {
        if(employeeDTO == null) {
            throw new BadRequestException("Employee is required");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("employee", employeeService.save(employeeDTO)))
                        .message("Employee created")
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PatchMapping
    public ResponseEntity<AppHttpResponse> updateEmployee(
            @RequestBody EmployeeDTO employeeDTO, HttpServletRequest request) {
        String subject = getSubjectFromHttpRequest(request);
        if(employeeDTO == null) {
            throw new BadRequestException("Employee is required");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("employee", employeeService.update(employeeDTO, subject)))
                        .message("Employee updated")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @DeleteMapping
    public ResponseEntity<AppHttpResponse> deleteEmployee(HttpServletRequest request) {
        String subject = getSubjectFromHttpRequest(request);
        employeeService.deleteByEmail(subject);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("employee", subject))
                        .message("Employee deleted")
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
