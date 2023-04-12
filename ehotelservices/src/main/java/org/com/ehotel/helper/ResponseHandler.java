package org.com.ehotel.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Component @AllArgsConstructor
public class ResponseHandler {
    private final ObjectMapper objectMapper;
    public ResponseEntity<AppHttpResponse> httpResponse(
            AppHttpResponse AppHttpResponse , HttpHeaders headers) {
        if(headers == null) {
            headers = new HttpHeaders();
            headers.add("Content-Type", APPLICATION_JSON_VALUE);
        }
        try {
            return new ResponseEntity<>(AppHttpResponse, headers,
                    AppHttpResponse.getStatus());
        } catch (Exception e) {
            return InternalServerErrorResponse(e);
        }
    }

    public ResponseEntity<AppHttpResponse> InternalServerErrorResponse(Exception e) {
        return new ResponseEntity<>(internalServerErrorResponse(e), INTERNAL_SERVER_ERROR);
    }

    private AppHttpResponse internalServerErrorResponse(Exception e) {
        return AppHttpResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .success(false)
                .data(new HashMap<>())
                .build();
    }

    public HttpServletResponse jsonResponse (AppHttpResponse AppHttpResponse,
                                             HttpServletResponse response) {
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setHeader("Access-Control-Allow-Origin", "localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        System.out.println("ResponseHandler.jsonResponse");
        try {
            String json = objectMapper.writeValueAsString(
                    AppHttpResponse);
            response.getWriter().write(json);
            response.setStatus(AppHttpResponse.getStatus().value());
            response.getWriter().flush();
            return response;
        } catch (Exception e) {
            String errJson = null;
            try {
                errJson = objectMapper
                        .writeValueAsString(internalServerErrorResponse(e));
                response.getWriter().write(errJson);
                response.setStatus(INTERNAL_SERVER_ERROR.value());
                response.getWriter().flush();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            return response;
        }
    }
}


