package com.moazmahmud.spring_boot_rest_api.customer;

import com.moazmahmud.spring_boot_rest_api.common.BaseRestController;
import com.moazmahmud.spring_boot_rest_api.common.RestResponse;
import com.moazmahmud.spring_boot_rest_api.customer.Customer;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor
public class CustomerController extends BaseRestController {

    private final CustomerService customerService;

    @GetMapping
    public RestResponse getCustomers() {
        List<CustomerResponse> customers = customerService.getCustomers();
        return RestResponse
                .builder()
                .success(true)
                .time(LocalDateTime.now())
                .payload(customers)
                .build();
    }

    @GetMapping("/{id}")
    public RestResponse getCustomer(
            @PathVariable("id") Long id
    ) {
        CustomerResponse customer = customerService.getCustomer(id);
        return RestResponse
                .builder()
                .success(true)
                .time(LocalDateTime.now())
                .payload(customer)
                .build();
    }

    @PostMapping
    public ResponseEntity<Void> addCustomer(
            @RequestBody CustomerAddRequest customerAddRequest
    ) throws URISyntaxException {
        Customer customer = customerService.addCustomer(customerAddRequest);
        String createdAt = "/api/v1/customers/" + customer.getId();
        return ResponseEntity
                .created(new URI(createdAt))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(
            @PathVariable("id") Long id,
            @RequestBody CustomerAddRequest customerAddRequest
    ) {
        customerService.updateCustomer(id, customerAddRequest);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(
            @PathVariable("id") Long id
    ) {
        customerService.deleteCustomer(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
