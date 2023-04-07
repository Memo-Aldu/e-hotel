package org.com.ehotel.controller.room;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.com.ehotel.dto.room.IncidentDTO;
import org.com.ehotel.exceptions.BadRequestException;
import org.com.ehotel.helper.AppHttpResponse;
import org.com.ehotel.helper.ResponseHandler;
import org.com.ehotel.service.room.IncidentService;
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
 * @created : 2023-03-28, Tuesday
 **/
@RestController @Slf4j @CrossOrigin("*")
@AllArgsConstructor @RequestMapping("/api/v1/incidents")
public class IncidentController {
    private final IncidentService incidentService;
    private final ResponseHandler responseHandler;

    @GetMapping("/room/{roomId}")
    public ResponseEntity<AppHttpResponse> getIncidentsByRoomId(
            @PathVariable Integer roomId, HttpServletRequest request) {
        if(roomId == null) {
            throw new BadRequestException("Invalid room id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "incidents", incidentService
                                        .getIncidentsByRoomId(roomId),
                                "roomId", roomId))
                        .message("Incidents found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppHttpResponse> getIncident(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid commodity id");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "incident", incidentService
                                        .getIncidentById(id)))
                        .message("Incident found")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PostMapping
    public ResponseEntity<AppHttpResponse> createIncident(
            @RequestBody IncidentDTO incident, HttpServletRequest request) {
        if(!incident.isValidDTO()) {
            throw new BadRequestException("Invalid incident");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "incident", incidentService
                                        .createIncident(incident)))
                        .message("Incident created")
                        .status(HttpStatus.CREATED)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AppHttpResponse> updateIncident(
            @PathVariable Integer id, @RequestBody IncidentDTO incident,
            HttpServletRequest request) {
        if(id == null || !incident.isValidDTO()) {
            throw new BadRequestException("Invalid incident");
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "incident", incidentService
                                        .updateIncident(incident, id)))
                        .message("Incident updated")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppHttpResponse> deleteIncident(
            @PathVariable Integer id, HttpServletRequest request) {
        if(id == null) {
            throw new BadRequestException("Invalid incident id");
        }
        incidentService.deleteIncidentById(id);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "incidentId", id))
                        .message("Incident deleted")
                        .status(HttpStatus.OK)
                        .success(true)
                        .timestamp(LocalDateTime.now())
                        .build(),
                setupResponseHeaders(request)
        );
    }

    @DeleteMapping("/room/{roomId}")
    public ResponseEntity<AppHttpResponse> deleteIncidentsByRoomId(
            @PathVariable Integer roomId, HttpServletRequest request) {
        if(roomId == null) {
            throw new BadRequestException("Invalid room id");
        }
        incidentService.deleteAllByRoomId(roomId);
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of(
                                "roomId", roomId))
                        .message("Incidents deleted")
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
