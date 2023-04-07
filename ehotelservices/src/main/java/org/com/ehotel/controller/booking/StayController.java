package org.com.ehotel.controller.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.booking.StayDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.booking.StayService;
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
@RestController @CrossOrigin("*") @Slf4j
@AllArgsConstructor @RequestMapping("/api/v1/stay")
public class StayController {
    private final StayService stayService;
    private final ResponseHandler responseHandler;

    @GetMapping("/{id}")
    public ResponseEntity<AppHttpResponse> getReservationById(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("stay", stayService.findStayById(id)))
                        .message("Stay found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/customer/{customerNAS}")
    public ResponseEntity<AppHttpResponse> getReservationsByCustomerNAS(
            @PathVariable String customerNAS, HttpServletRequest request) {
        if (customerNAS == null) {
            throw new BadRequestException("Invalid customer NAS");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("stays", stayService.findStaysByCustomerNAS(customerNAS)))
                        .message("Stays found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<AppHttpResponse> getReservationsByHotelId(
            @PathVariable Integer hotelId, HttpServletRequest request) {
        if(hotelId == null) {
            throw new BadRequestException("Invalid hotel id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("stays", stayService.findStayByHotelId(hotelId)))
                        .message("Stays found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PostMapping
    public ResponseEntity<AppHttpResponse> createReservation(
            @RequestBody StayDTO stayDTO, HttpServletRequest request) {
        if(stayDTO == null) {
            throw new BadRequestException("Invalid stay");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("stay", stayService.create(stayDTO)))
                        .message("Stay created")
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppHttpResponse> updateReservation(
            @RequestBody StayDTO stayDTO, @PathVariable Integer id, HttpServletRequest request) {
        if(stayDTO == null || id == null) {
            throw new BadRequestException("Invalid stay");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("stay", stayService.update(stayDTO, id)))
                        .message("Stay updated")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppHttpResponse> deleteReservation(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid id");
        }
        stayService.deleteStayByStayId(id);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("stay", id))
                        .message("Stay deleted")
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
