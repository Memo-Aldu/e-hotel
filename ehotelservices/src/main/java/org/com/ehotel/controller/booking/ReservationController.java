package org.com.ehotel.controller.booking;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.booking.ReservationDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.booking.ReservationService;
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
 * @created : 2023-04-06, Thursday
 **/
@AllArgsConstructor @Slf4j
@RestController @RequestMapping("/api/v1/reservation")
@CrossOrigin(
        allowCredentials = "true",
        origins = "http://localhost:3000",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT
                , RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class ReservationController {
    private final ReservationService reservationService;
    private final ResponseHandler responseHandler;

    @GetMapping("/{id}")
    public ResponseEntity<AppHttpResponse> getReservationById(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("reservation", reservationService.findReservationById(id)))
                        .message("Reservation found")
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
        if(customerNAS == null) {
            throw new BadRequestException("Invalid customer NAS");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("reservations", reservationService.findReservationsByCustomerNAS(customerNAS)))
                        .message("Reservations found")
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
                        .data(Map.of("reservations", reservationService.findReservationByHotelId(hotelId)))
                        .message("Reservations found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PostMapping
    public ResponseEntity<AppHttpResponse> createReservation(
            @RequestBody ReservationDTO reservation, HttpServletRequest request) {
        if(reservation == null) {
            throw new BadRequestException("Invalid reservation");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("reservation", reservationService.create(reservation)))
                        .message("Reservation created")
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppHttpResponse> updateReservation(
            @PathVariable Integer id, @RequestBody ReservationDTO reservation, HttpServletRequest request) {
        if(id == null || reservation == null) {
            throw new BadRequestException("Invalid reservation");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("reservation", reservationService.update(reservation, id)))
                        .message("Reservation updated")
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
            throw new BadRequestException("Invalid reservation id");
        }
        reservationService.deleteReservationByReservationId(id);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("reservation", id))
                        .message("Reservation deleted")
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
