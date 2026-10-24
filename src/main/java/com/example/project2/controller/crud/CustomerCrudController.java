package com.example.project2.controller.crud;

import com.example.project2.dto.CustomerDto;
import com.example.project2.model.Customer;
import com.example.project2.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerCrudController {
    private final CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        try {
            Customer customer = new Customer();
            customer.setName(customerDto.getName());
            customer.setPhone(customerDto.getPhone());
            customer.setAddress(customerDto.getAddress());

            Customer savedCustomer = customerRepository.save(customer);

            CustomerDto responseDto = new CustomerDto(
                    savedCustomer.getId(),
                    savedCustomer.getName(),
                    savedCustomer.getPhone(),
                    savedCustomer.getAddress()
            );

            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
