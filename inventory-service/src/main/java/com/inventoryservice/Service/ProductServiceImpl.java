package com.inventoryservice.Service;

import com.inventoryservice.dto.ProductRequestDTO;
import com.inventoryservice.dto.ProductResponseDTO;
import com.inventoryservice.entities.Product;
import com.inventoryservice.exception.ProductNotFoundException;
import com.inventoryservice.mapper.ProductMapper;
import com.inventoryservice.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService{
    private ProductMapper productMapper;
    private ProductRepository productRepository;

    @Override
    public ProductResponseDTO save(ProductRequestDTO productRequestDTO) {
        Product newProduct = productMapper.fromProductRequestDTO(productRequestDTO);
        Product savedProduct = productRepository.save(newProduct);
        return productMapper.fromProduct(savedProduct);
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO) throws ProductNotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product not found"));
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantity(productRequestDTO.getQuantity());

        Product productUpdated = productRepository.save(product);
        return productMapper.fromProduct(productUpdated);
    }

    @Override
    public ProductResponseDTO getProductById(Long id) throws ProductNotFoundException {
        Product productFound = productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
        return productMapper.fromProduct(productFound);
    }

    @Override
    public List<ProductResponseDTO> listProducts() {
        return productRepository.findAll().stream().map(productMapper::fromProduct).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) throws ProductNotFoundException {
        Product productFound = productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
        productRepository.delete(productFound);
    }

    @Override
    public List<ProductResponseDTO> findProduct(String keyword) {
        return productRepository.findAll().stream()
                .filter(p->p.getName().toLowerCase().contains(keyword.toLowerCase()))
                .map(productMapper::fromProduct).collect(Collectors.toList());
    }
}
