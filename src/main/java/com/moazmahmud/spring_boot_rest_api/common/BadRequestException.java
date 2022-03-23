package com.moazmahmud.spring_boot_rest_api.common;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
