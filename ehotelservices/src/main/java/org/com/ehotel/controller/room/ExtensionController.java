package org.com.ehotel.controller.room;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.room.ExtensionDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.room.ExtensionService;
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
 * @created : 3/27/2023, Monday
 **/
@RestController @Slf4j @CrossOrigin("*")
@AllArgsConstructor @RequestMapping("/api/v1/extension")
public class ExtensionController {
    private final ExtensionService extensionService;
    private final ResponseHandler responseHandler;

    @GetMapping("/room/{roomId}")
    public ResponseEntity<AppHttpResponse> getExtensionsByRoomId(
            @PathVariable Integer roomId, HttpServletRequest request) {
        if(roomId == null) {
            throw new BadRequestException("Room id cannot be null");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .success(true)
                        .message("Extensions fetched successfully")
                        .data(Map.of("extensions", extensionService
                                .getExtensionsByRoomId(roomId)))
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .build(),
                setupResponseHeaders(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppHttpResponse> getExtensionById(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Extension id cannot be null");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .success(true)
                        .message("Extension fetched successfully")
                        .data(Map.of("extension", extensionService
                                .getExtensionById(id)))
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .build(),
                setupResponseHeaders(request));
    }

    @PostMapping
    public ResponseEntity<AppHttpResponse> updateExtension(
            @RequestBody ExtensionDTO dto, HttpServletRequest request) {
        if(!dto.isValidDto()) {
            throw new BadRequestException("Invalid extension");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .success(true)
                        .message("Extension updated successfully")
                        .data(Map.of("extension", extensionService
                                .createExtension(dto)))
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.CREATED)
                        .build(),
                setupResponseHeaders(request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppHttpResponse> updateExtension(
            @PathVariable Integer id, @RequestBody ExtensionDTO dto,
            HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Extension id cannot be null");
        }
        if(!dto.isValidDto()) {
            throw new BadRequestException("Invalid extension");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .success(true)
                        .message("Extension updated successfully")
                        .data(Map.of("extension", extensionService
                                .updateExtension(dto, id)))
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .build(),
                setupResponseHeaders(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppHttpResponse> deleteExtension(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Extension id cannot be null");
        }
        extensionService.deleteExtensionById(id);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .success(true)
                        .message("Extension deleted successfully")
                        .data(Map.of("room", id))
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .build(),
                setupResponseHeaders(request));
    }

    @DeleteMapping("/room/{roomId}")
    public ResponseEntity<AppHttpResponse> deleteExtensionsByRoomId(
            @PathVariable Integer roomId, HttpServletRequest request) {
        if(roomId == null) {
            throw new BadRequestException("Room id cannot be null");
        }
        extensionService.deleteAllByRoomId(roomId);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .success(true)
                        .message("Extensions deleted successfully")
                        .data(Map.of("room", roomId))
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.OK)
                        .build(),
                setupResponseHeaders(request));
    }

    private HttpHeaders setupResponseHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(CONTENT_TYPE, "application/json");
        headers.set(AUTHORIZATION, request.getHeader(AUTHORIZATION));
        return headers;
    }

}
