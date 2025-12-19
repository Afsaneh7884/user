package com.ecommerce.user.controller;

import com.ecommerce.user.model.Customer;
import com.ecommerce.user.service.CustomerService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/api/customers")
    public ResponseEntity<List<Customer>> getAllCustomers(){
       // return new ResponseEntity<>(customerService.fetchAllCustomers(), HttpStatus.OK);
        return ResponseEntity.ok(customerService.fetchAllCustomers());
    }

    @GetMapping("/api/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id){
//        if (customerService.fetchCustomer(id) == null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(customerService.fetchCustomer(id));

        return customerService.fetchCustomer(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/api/customers")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
        customerService.addCustomer(customer);
        return ResponseEntity.ok("User added Ok ");
    }

    @PutMapping("/api/customers/{id}")
    public ResponseEntity<String> updateCustomer(@PathVariable Long id,@RequestBody Customer updatedCustomeer){
        boolean updated = customerService.updateCustomer(id,updatedCustomeer);
        if (updated)
            return ResponseEntity.ok("User added succesfully");
        return  ResponseEntity.notFound().build();

    }


}
