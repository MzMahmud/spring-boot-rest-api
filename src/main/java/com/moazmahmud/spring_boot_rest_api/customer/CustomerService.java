package com.moazmahmud.spring_boot_rest_api.customer;

import com.moazmahmud.spring_boot_rest_api.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Optional<Customer> findById(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return customerRepository.findById(id);
    }

    private CustomerResponse mapEntityToResponse(Customer Customer) {
        CustomerResponse response = new CustomerResponse();
        response.setId(Customer.getId());
        response.setName(Customer.getName());
        response.setPhoneNumber(Customer.getPhoneNumber());
        response.setEmail(Customer.getEmail());
        response.setAddress(Customer.getAddress());
        return response;
    }

    @Transactional(readOnly = true)
    public List<CustomerResponse> getCustomers() {
        return customerRepository
                .findAll()
                .stream()
                .map(this::mapEntityToResponse)
                .collect(Collectors.toUnmodifiableList());
    }

    public CustomerResponse getCustomer(Long id) {
        return findById(id)
                .map(this::mapEntityToResponse)
                .orElseThrow(() -> new NotFoundException("No Customer found with id=" + id));
    }

    private Customer mapAddRequestToEntity(CustomerAddRequest addRequest,
                                           Long id,
                                           Customer customer) {
        customer.setId(id);
        customer.setName(addRequest.getName());
        customer.setPhoneNumber(addRequest.getPhoneNumber());
        customer.setEmail(addRequest.getEmail());
        customer.setAddress(addRequest.getAddress());
        return customer;
    }

    @Transactional
    public Customer addCustomer(CustomerAddRequest CustomerAddRequest) {
        Customer customer = mapAddRequestToEntity(CustomerAddRequest, null, new Customer());
        return customerRepository.save(customer);
    }

    @Transactional
    public void updateCustomer(Long id, CustomerAddRequest CustomerAddRequest) {
        Customer customer = findById(id).orElseThrow(() -> new NotFoundException("No Customer found with id=" + id));
        mapAddRequestToEntity(CustomerAddRequest, id, customer);
    }

    public void deleteCustomer(Long id) {
        Customer customer = findById(id).orElseThrow(() -> new NotFoundException("No Customer found with id=" + id));
        customerRepository.delete(customer);
    }
}
