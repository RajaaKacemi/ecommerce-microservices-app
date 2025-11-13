package com.billingservice.feign;

import com.billingservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
// OpenFeign est un framework declarative -> on a pas besion de programmer.
public interface CustomerServiceRestClient {
    @GetMapping("/customers/{id}")
    @CircuitBreaker(name="customer-service", fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable Long id);

    default Customer getDefaultCustomer(Long id, Exception exception){
        exception.printStackTrace();
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName("default customer first name");
        customer.setLastName("default customer last name");
        customer.setEmail("default@gmail.com");
        return customer;

    }
}
