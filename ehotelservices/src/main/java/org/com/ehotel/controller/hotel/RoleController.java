package org.com.ehotel.controller.hotel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.hotel.RoleDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.hotel.RoleService;
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
 * @created : 4/7/2023, Friday
 **/
@RestController @CrossOrigin(origins = "*") @Slf4j
@AllArgsConstructor @RequestMapping("/api/v1/role")
public class RoleController {
    private final RoleService roleService;
    private final ResponseHandler responseHandler;

    @GetMapping("/{id}")
    public ResponseEntity<AppHttpResponse> getRoleByID(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid role id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "role", roleService.findById(id),
                                "roleId", id))
                        .message("Role found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<AppHttpResponse> getRolesByHotelId(
            @PathVariable Integer hotelId, HttpServletRequest request) {
        if(hotelId == null) {
            throw new BadRequestException("Invalid hotel id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "roles", roleService.findByHotelId(hotelId),
                                "hotelId", hotelId))
                        .message("Roles found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PostMapping
    public ResponseEntity<AppHttpResponse> createRole(
            @RequestBody RoleDTO role, HttpServletRequest request) {
        if(role == null) {
            throw new BadRequestException("Invalid role");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("role", roleService.create(role)))
                        .message("Role created")
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppHttpResponse> updateRole(
            @PathVariable Integer id, @RequestBody RoleDTO role, HttpServletRequest request) {
        if(id == null || role == null) {
            throw new BadRequestException("Invalid role");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("role", roleService.update(role, id)))
                        .message("Role updated")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppHttpResponse> deleteRole(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid role id");
        }
        roleService.deleteById(id);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("roleId", id))
                        .message("Role deleted")
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
}
