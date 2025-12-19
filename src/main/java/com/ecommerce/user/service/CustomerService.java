package com.ecommerce.user.service;

import com.ecommerce.user.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    List<Customer> customerList = new ArrayList<>();

    private Long nextId = 1L;


    public CustomerService(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> fetchAllCustomers(){
        System.out.println(customerList.size());
        System.out.println("reading customer");
        return customerList;
    }

    public void addCustomer(Customer customer){
        customer.setCustomerId(nextId++);
        customerList.add(customer);
        System.out.println(customerList);

    }

    public Optional<Customer> fetchCustomer(Long id) {
        return customerList.stream().filter(
                user-> user.getCustomerId().equals(id))
                        .findFirst();
    }

    public boolean updateCustomer(Long id, Customer updatedCustomeer){
        return customerList.stream().filter(customer->customer.getCustomerId().equals(id))
                .findFirst()
                .map(existingCustomer -> {
                    existingCustomer.setName(updatedCustomeer.getName());
                    existingCustomer.setCustomerGender(updatedCustomeer.getCustomerGender());
                    return true;
                }).orElse(false);




    }


}
