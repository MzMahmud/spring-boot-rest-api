package com.moazmahmud.spring_boot_rest_api.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class BaseRestController {
    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<RestResponse> handleBadRequest(BadRequestException badRequestException) {
        return ResponseEntity
                .badRequest()
                .body(RestResponse
                        .builder()
                        .success(false)
                        .message(badRequestException.getMessage())
                        .time(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<RestResponse> handleNotFound(NotFoundException notFoundException) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(RestResponse
                        .builder()
                        .success(false)
                        .message(notFoundException.getMessage())
                        .time(LocalDateTime.now())
                        .build()
                );
    }

    @ExceptionHandler({InternalServerException.class, Exception.class})
    public ResponseEntity<RestResponse> handleException(Exception exception) {
        return ResponseEntity
                .internalServerError()
                .body(RestResponse
                        .builder()
                        .success(false)
                        .message(exception.getMessage())
                        .time(LocalDateTime.now())
                        .build()
                );
    }
}
