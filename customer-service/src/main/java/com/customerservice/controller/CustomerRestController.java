package com.customerservice.controller;

import com.customerservice.entities.Customer;
import com.customerservice.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {
    private CustomerRepository customerRepository;

    public CustomerRestController(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> listCustomers(){
        return customerRepository.findAll();
    }

    @PostMapping("/customers")
    public Customer saverCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }
}
