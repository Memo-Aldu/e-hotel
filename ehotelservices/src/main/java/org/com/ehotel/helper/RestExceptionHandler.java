package org.com.ehotel.helper;

import lombok.AllArgsConstructor;
import org.com.ehotel.exceptions.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice @AllArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final ResponseHandler responseHandler;

    @ExceptionHandler(AppEntityNotFoundException.class)
    protected ResponseEntity<AppHttpResponse> handleEntityNotFound(
            AppEntityNotFoundException ex) {
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("error", ex.getMessage()))
                        .timestamp(LocalDateTime.now())
                        .status(NOT_FOUND)
                        .success(false)
                        .message(ex.getLocalizedMessage())
                        .build(),
                headers(ex.getMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<AppHttpResponse> handleBadRequest(
            BadRequestException ex) {

        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("error", ex.getMessage()))
                        .timestamp(LocalDateTime.now())
                        .status(BAD_REQUEST)
                        .success(false)
                        .message(ex.getLocalizedMessage())
                        .build(),
                headers(ex.getMessage()));
    }

    @ExceptionHandler(AppEntityAlreadyExistException.class)
    protected ResponseEntity<AppHttpResponse> handleEntityAlreadyExist(
            AppEntityAlreadyExistException ex) {
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("error", ex.getMessage()))
                        .timestamp(LocalDateTime.now())
                        .status(CONFLICT)
                        .success(false)
                        .message(ex.getLocalizedMessage())
                        .build(),
                headers(ex.getMessage()));
    }

    @ExceptionHandler({TokenAuthenticationException.class,
            InvalidTokenException.class, MissingAuthenticationException.class})
    protected ResponseEntity<AppHttpResponse> handleTokenAuthenticationException(
            RuntimeException ex) {
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("error", ex.getMessage()))
                        .timestamp(LocalDateTime.now())
                        .status(UNAUTHORIZED)
                        .success(false)
                        .message(ex.getLocalizedMessage())
                        .build(),
                headers(ex.getMessage()));
    }

    @ExceptionHandler(UnauthorizedTokenException.class)
    protected ResponseEntity<AppHttpResponse> handleUnauthorizedException(
            UnauthorizedTokenException ex) {
        if(ex.getScope() == null) {
            return responseHandler.httpResponse(
                    AppHttpResponse.builder()
                            .data(Map.of("error", ex.getMessage()))
                            .timestamp(LocalDateTime.now())
                            .status(OK)
                            .success(false)
                            .message(ex.getLocalizedMessage())
                            .build(),
                    headers(ex.getMessage()));
        }
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("error", ex.getMessage()))
                        .timestamp(LocalDateTime.now())
                        .status(OK)
                        .success(true)
                        .data(Map.of(ex.getScope(), false))
                        .message(ex.getLocalizedMessage())
                        .build(),
                headers(ex.getMessage()));
    }

    @ExceptionHandler(HttpServerErrorException.class)
    protected ResponseEntity<AppHttpResponse> handleHttpServerErrorException(
            HttpServerErrorException ex) {
        return responseHandler.httpResponse(
                AppHttpResponse.builder()
                        .data(Map.of("error", Objects.requireNonNull(ex.getMessage())))
                        .timestamp(LocalDateTime.now())
                        .status(INTERNAL_SERVER_ERROR)
                        .success(false)
                        .message(ex.getLocalizedMessage())
                        .build(),
                headers(ex.getMessage()));
    }


    private HttpHeaders headers(String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        String ERROR_TYPE_HEADER = "error_type";
        headers.add(ERROR_TYPE_HEADER, message);
        return headers;
    }
}
