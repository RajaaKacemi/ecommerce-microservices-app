package com.inventoryservice.mapper;

import com.inventoryservice.dto.ProductRequestDTO;
import com.inventoryservice.dto.ProductResponseDTO;
import com.inventoryservice.entities.Product;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;

@Mapper(componentModel = "spring")
public class ProductMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public ProductResponseDTO fromProduct(Product product) {
        return modelMapper.map(product, ProductResponseDTO.class);
    }

    public Product fromProductRequestDTO(ProductRequestDTO productRequestDTO) {
        return modelMapper.map(productRequestDTO, Product.class);
    }
}
