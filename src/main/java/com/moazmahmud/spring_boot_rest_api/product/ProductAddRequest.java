package com.moazmahmud.spring_boot_rest_api.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddRequest {
    private String name;
    private String description;
    private Long price;
}
