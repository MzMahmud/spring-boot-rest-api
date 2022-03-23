package com.moazmahmud.spring_boot_rest_api.common;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RestResponse {
    private Boolean success;
    private String message;
    private LocalDateTime time;
    private Object payload;
}
