package org.com.ehotel.controller.hotel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.hotel.DepartmentDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.hotel.DepartmentService;
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
@AllArgsConstructor @RequestMapping("/api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final ResponseHandler responseHandler;

    @GetMapping("/{id}")
    public ResponseEntity<AppHttpResponse> getDepartmentByID(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid department id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "department", departmentService.findById(id),
                                "departmentId", id))
                        .message("Department found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<AppHttpResponse> getDepartmentsByHotelId(
            @PathVariable Integer hotelId, HttpServletRequest request) {
        if(hotelId == null) {
            throw new BadRequestException("Invalid hotel id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "departments", departmentService.findByHotelId(hotelId),
                                "hotelId", hotelId))
                        .message("Departments found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/manager/{managerNAS}")
    public ResponseEntity<AppHttpResponse> getDepartmentsByManagerId(
            @PathVariable String managerNAS, HttpServletRequest request) {
        if(managerNAS == null || managerNAS.isEmpty()) {
            throw new BadRequestException("Invalid manager id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "departments", departmentService.findByManagerNAS(managerNAS),
                                "managerNAS", managerNAS))
                        .message("Department found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PostMapping
    public ResponseEntity<AppHttpResponse> createDepartment(
            @RequestBody DepartmentDTO department, HttpServletRequest request) {
        if(department == null) {
            throw new BadRequestException("Invalid department");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "department", departmentService.create(department)))
                        .message("Department created")
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppHttpResponse> updateDepartment(
            @PathVariable Integer id, @RequestBody DepartmentDTO department, HttpServletRequest request) {
        if(id == null || department == null) {
            throw new BadRequestException("Invalid department");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "department", departmentService.update(department, id)))
                        .message("Department updated")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppHttpResponse> deleteDepartment(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid department id");
        }
        departmentService.deleteById(id);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "departmentId", id))
                        .message("Department deleted")
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
