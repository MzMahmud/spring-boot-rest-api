package com.moazmahmud.spring_boot_rest_api.common;

public class InternalServerException extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
