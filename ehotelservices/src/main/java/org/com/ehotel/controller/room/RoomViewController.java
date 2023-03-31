package org.com.ehotel.controller.room;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.room.RoomViewDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.room.RoomViewService;
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
 * @created : 3/29/2023, Wednesday
 **/
@RestController @AllArgsConstructor
@Slf4j @RequestMapping("/api/v1/room-view")
public class RoomViewController {
    private final RoomViewService roomViewService;
    private final ResponseHandler responseHandler;

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<AppHttpResponse> getRoomViewsByHotelId(
            @PathVariable("hotelId") Integer hotelId, HttpServletRequest request) {
        if(hotelId == null) {
            throw new BadRequestException("Invalid hotel id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "roomViews", roomViewService.getRoomViewsByHotelId(hotelId),
                                "hotelId", hotelId))
                        .message("Room views found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppHttpResponse> getRoomView(
            @PathVariable("id") Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid room view id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "roomView", roomViewService.getRoomViewById(id),
                                "id", id))
                        .message("Room view found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PostMapping
    public ResponseEntity<AppHttpResponse> createRoomView(
            @RequestBody RoomViewDTO roomView, HttpServletRequest request) {
        if(roomView == null) {
            throw new BadRequestException("Invalid room view");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "roomView", roomViewService.createRoomView(roomView)))
                        .message("Room view created")
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppHttpResponse> updateRoomView(
            @PathVariable("id") Integer id, @RequestBody RoomViewDTO roomView, HttpServletRequest request) {
        if(id == null || roomView == null) {
            throw new BadRequestException("Invalid room view");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "roomView", roomViewService.updateRoomView(roomView, id)))
                        .message("Room view updated")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppHttpResponse> deleteRoomView(
            @PathVariable("id") Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid room view id");
        }
        roomViewService.deleteRoomViewById(id);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "id", id))
                        .message("Room view deleted")
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
