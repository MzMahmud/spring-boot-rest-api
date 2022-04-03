package com.moazmahmud.spring_boot_rest_api.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddRequest {
    private String name;
    private String phoneNumber;
    private String email;
    private String address;
}
