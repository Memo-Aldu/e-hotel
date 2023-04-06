package org.com.ehotel.controller.hotel;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.hotel.ChainHotelDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.hotel.ChainHotelService;
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
 * @created : 4/4/2023, Tuesday
 **/
@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/hotel-chain")
@CrossOrigin("*")
public class ChainHotelController {
    private final ChainHotelService chainService;
    private final ResponseHandler responseHandler;


    @GetMapping("/{id}")
    public ResponseEntity<AppHttpResponse> getChainHotelByID(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "hotel chain", chainService.getChainHotelById(id),
                                "ChainId", id))
                        .message("Chain found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }
    @GetMapping()
    public ResponseEntity<AppHttpResponse> getAllHotelChains( HttpServletRequest request) {

        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "Chain", chainService.getAllChainHotelEntity()))
                        .message("Chain found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }
    @PatchMapping("/{id}")
    public ResponseEntity<AppHttpResponse> updateChainHotel(
            @PathVariable Integer id, @RequestBody ChainHotelDTO chainHotelDTO,
            HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Hotel chain id cannot be null");
        }
        if(chainHotelDTO == null || !chainHotelDTO.isValidDto()) {
            throw new BadRequestException("Invalid hotel chain");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .success(true)
                        .message("Hotel updated successfully")
                        .data(Map.of("chain", chainService
                                .updateChainHotel(chainHotelDTO, id)))
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .build(),
                setupResponseHeaders(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppHttpResponse> deleteChainHotel(
            @PathVariable Integer id, HttpServletRequest request) {
        if (id == null) {
            throw new BadRequestException("Invalid chain id");
        }
        chainService.deleteChainHotelByID(id);
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

    @PostMapping()
    public ResponseEntity<AppHttpResponse> createHotelChain(
            @RequestBody ChainHotelDTO chainDTO, HttpServletRequest request) {
        if(chainDTO == null || !chainDTO.isValidDto()) {
            throw new BadRequestException("Invalid hotel chain");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("chain", chainService
                                .createChainHotel(chainDTO)))
                        .message("Hotel chain created")
                        .status(HttpStatus.CREATED)
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
