package com.inventoryservice.controller;

import com.inventoryservice.Service.ProductService;
import com.inventoryservice.dto.ProductRequestDTO;
import com.inventoryservice.dto.ProductResponseDTO;
import com.inventoryservice.exception.ProductNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
public class ProductRestController {
    private ProductService productService;

    @GetMapping
    public ProductResponseDTO save(@RequestParam ProductRequestDTO product){
        return productService.save(product);
    }

    @GetMapping
    public List<ProductResponseDTO> list(){
        return productService.listProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO get(@PathVariable Long id) throws ProductNotFoundException {
        return productService.getProductById(id);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO update(@PathVariable Long id, @RequestParam ProductRequestDTO productUpdated) throws ProductNotFoundException {
        return productService.update(id, productUpdated);
    }

    @GetMapping("/{id}")
    public void delete(@PathVariable Long id) throws ProductNotFoundException {
        productService.delete(id);
    }

    public List<ProductResponseDTO> search(@RequestParam String keyword){
        return productService.findProduct(keyword);
    }
}
