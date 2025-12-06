package com.customerservice;

import com.customerservice.config.CustomerConfigParams;
import com.customerservice.entitie.Customer;
import com.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(CustomerConfigParams.class)
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder().firstname("Rajaa").lastname("kacemi").email("rajaa@gmail.com").build());
            customerRepository.save(Customer.builder().firstname("Majda").lastname("kacemi").email("majda@gmail.com").build());
        };
    }

}
