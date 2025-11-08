package com.inventoryservice;

import com.inventoryservice.entities.Product;
import com.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder().name("TV").price(2500.0).quantity(40).build());
            productRepository.save(Product.builder().name("PC").price(3500.0).quantity(20).build());
            productRepository.save(Product.builder().name("Clavier").price(100.0).quantity(60).build());
        };
    }

}
