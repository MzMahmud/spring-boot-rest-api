package com.moazmahmud.spring_boot_rest_api.common;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
