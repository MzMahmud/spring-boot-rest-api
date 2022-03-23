package com.moazmahmud.spring_boot_rest_api.common;

import lombok.Data;

@Data
public class BaseSearchRequest {
    public Integer pageNo;
    public Integer pageSize;
}
