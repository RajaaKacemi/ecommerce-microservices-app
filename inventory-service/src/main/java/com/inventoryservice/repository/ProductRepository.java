package com.inventoryservice.repository;

import com.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
