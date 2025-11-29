package com.inventoryservice.Service;

import com.inventoryservice.dto.ProductRequestDTO;
import com.inventoryservice.dto.ProductResponseDTO;
import com.inventoryservice.exception.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    ProductResponseDTO save(ProductRequestDTO productRequestDTO);
    ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO) throws ProductNotFoundException;
    ProductResponseDTO getProductById(Long id) throws ProductNotFoundException;
    List<ProductResponseDTO> listProducts();
    void delete(Long id) throws ProductNotFoundException;
    List<ProductResponseDTO> findProduct(String keyword);

}
