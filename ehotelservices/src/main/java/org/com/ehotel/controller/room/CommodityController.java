package org.com.ehotel.controller.room;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.room.CommodityDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.room.CommodityService;
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
 * @created : 3/26/2023, Sunday
 **/
@RestController @AllArgsConstructor
@RequestMapping("api/v1/commodities") @Slf4j
public class CommodityController {
    private final CommodityService commodityService;
    private final ResponseHandler responseHandler;

    @GetMapping("/room/{roomId}")
    public ResponseEntity<AppHttpResponse> getCommoditiesByRoomId(
            @PathVariable Integer roomId, HttpServletRequest request) {
        if(roomId == null) {
            throw new BadRequestException("Invalid room id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "commodities", commodityService
                                        .getCommoditiesByRoomId(roomId),
                                "roomId", roomId))
                        .message("Commodities found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppHttpResponse> getCommodity(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid commodity id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("commodity", commodityService
                                .getCommodityById(id)))
                        .message("Commodities found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PostMapping()
    public ResponseEntity<AppHttpResponse> createCommodity(
            @RequestBody CommodityDTO commodityDTO, HttpServletRequest request) {
        if(commodityDTO == null || !commodityDTO.isValid()) {
            throw new BadRequestException("Invalid commodity");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("commodity", commodityService
                                .createCommodity(commodityDTO)))
                        .message("Commodity created")
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppHttpResponse> updateCommodity(
            @RequestBody CommodityDTO commodityDTO,
            @PathVariable Integer id, HttpServletRequest request) {
        if(commodityDTO == null || !commodityDTO.isValid()) {
            throw new BadRequestException("Invalid commodity");
        }
        if(id == null) {
            throw new BadRequestException("Invalid commodity id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("commodity", commodityService
                                .updateCommodity(commodityDTO, id)))
                        .message("Commodity updated")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @DeleteMapping("/room/{roomId}")
    public ResponseEntity<AppHttpResponse> deleteAllByRoomId(
            @PathVariable Integer roomId, HttpServletRequest request) {
        if(roomId == null) {
            throw new BadRequestException("Invalid room id");
        }
        commodityService.deleteAllByRoomId(roomId);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("roomId", roomId))
                        .message("Commodities deleted")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppHttpResponse> deleteCommodity(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid commodity id");
        }
        commodityService.deleteCommodityById(id);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("id", id))
                        .message("Commodity deleted")
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
