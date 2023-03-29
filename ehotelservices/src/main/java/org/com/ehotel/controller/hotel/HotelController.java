package org.com.ehotel.controller.hotel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.hotel.ChainHotelDTO;
import org.com.ehotel.dto.hotel.HotelDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.hotel.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpHeaders;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@RestController @AllArgsConstructor
@RequestMapping("/api/v1/hotel") @Slf4j
public class HotelController {

    private final HotelService hotelService;
    private final ResponseHandler responseHandler;

    @GetMapping("/{name}")
    public ResponseEntity<AppHttpResponse> getHotelByName(
            @PathVariable String name, HttpServletRequest request) {
        if(name == null) {
            throw new BadRequestException("Invalid name");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "Hotel", hotelService.getHotelEntityByName(name),
                                "Hotel name", name))
                        .message("Hotel found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppHttpResponse> getHotelByID(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "hotel", hotelService.getHotelEntityById(id),
                                "HotelId", id))
                        .message("Hotel found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }
    @GetMapping("/{address}")
    public ResponseEntity<AppHttpResponse> getHotelByAddress(
            @PathVariable String address, HttpServletRequest request) {
        if(address == null) {
            throw new BadRequestException("Invalid address");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "hotel", hotelService.getHotelEntityByAddress(address),
                                "Hotel address", address))
                        .message("Hotel found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/{rating}")
    public ResponseEntity<AppHttpResponse> getAllHotelByRating(
            @PathVariable short rating, HttpServletRequest request) {
        if(rating < 0) {
            throw new BadRequestException("Invalid address");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "hotel", hotelService.getAllHotelEntityByRating(rating),
                                "Hotel rating", rating))
                        .message("Hotel found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PostMapping()
    public ResponseEntity<AppHttpResponse> createHotel(
            @RequestBody HotelDTO hotelDTO, HttpServletRequest request) {
        if(hotelDTO == null || !hotelDTO.isValidDto()) {
            throw new BadRequestException("Invalid hotel");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("hotel", hotelService
                                .createHotel(hotelDTO)))
                        .message("Hotel created")
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }
    @PatchMapping("/{id}")
    public ResponseEntity<AppHttpResponse> updateHotel(
            @RequestBody HotelDTO hotelDTO,
            @PathVariable Integer id, HttpServletRequest request) {
        if(hotelDTO == null || !hotelDTO.isValidDto()) {
            throw new BadRequestException("Invalid hoetl");
        }
        if(id == null) {
            throw new BadRequestException("Invalid hotel id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("hotel", hotelService
                                .updateHotel(hotelDTO, id)))
                        .message("Hotel updated")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<AppHttpResponse> deleteHotel(
            @PathVariable Integer id, HttpServletRequest request) {
        if (id == null) {
            throw new BadRequestException("Invalid chain id");
        }
        hotelService.deleteHotelEntityById(id);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("id", id))
                        .message("Chain deleted")
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
