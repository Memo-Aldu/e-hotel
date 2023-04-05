package org.com.ehotel.controller.room;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.room.RoomDTO;
import org.com.ehotel.dto.room.RoomSearchRequest;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.room.RoomService;
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
 * @created : 3/23/2023, Thursday
 **/
@RestController @AllArgsConstructor
@RequestMapping("/api/v1/room") @Slf4j
public class RoomController {
    private final RoomService roomService;
    private final ResponseHandler responseHandler;

    @GetMapping("/search")
    public ResponseEntity<AppHttpResponse> searchRooms(
            @RequestBody RoomSearchRequest roomSearchRequest, HttpServletRequest request) {
        if (!roomSearchRequest.valid()) {
            throw new BadRequestException("Invalid search request");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("rooms", roomService.searchRooms(roomSearchRequest)))
                        .message("Rooms found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppHttpResponse> getRoomById(
            @PathVariable Integer id, HttpServletRequest request) {
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("room", roomService.getRoomById(id)))
                        .message("Room found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<AppHttpResponse> getRoomsByHotelId(
            @PathVariable Integer id, HttpServletRequest request) {
        if (id == null) {
            throw new BadRequestException("Invalid hotel id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("rooms", roomService.getRoomsByHotelId(id)))
                        .message("Rooms found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PostMapping()
    public ResponseEntity<AppHttpResponse> saveRoom(
            @RequestBody RoomDTO roomDTO, HttpServletRequest request) {
        if (roomDTO.isValid()) {
            throw new BadRequestException("Invalid room request");
        }
        log.info("Saving room: " + roomDTO);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("room", roomService.saveRoom(roomDTO)))
                        .message("Room saved")
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppHttpResponse> updateRoom(
            @RequestBody RoomDTO roomDTO, @PathVariable Integer id,HttpServletRequest request) {
        if (roomDTO.isValid() && id != null) {
            throw new BadRequestException("Invalid room request");
        }
        log.info("Updating room: " + roomDTO);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("room", roomService.updateRoom(roomDTO, id)))
                        .message("Room updated")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppHttpResponse> deleteRoom(
            @PathVariable Integer id, HttpServletRequest request) {
        log.info("Deleting room with id: " + id);
        roomService.deleteRoom(id);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("room", id))
                        .message("Room deleted")
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
