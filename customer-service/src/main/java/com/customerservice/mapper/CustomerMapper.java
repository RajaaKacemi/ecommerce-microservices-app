package com.customerservice.mapper;

import com.customerservice.dto.CustomerRequestDTO;
import com.customerservice.dto.CustomerResponseDTO;
import com.customerservice.entitie.Customer;
import org.modelmapper.ModelMapper;

public class CustomerMapper {
    private ModelMapper modelMapper = new ModelMapper();
    public CustomerResponseDTO from(Customer customer){
        return modelMapper.map(customer, CustomerResponseDTO.class);
    }
    public Customer from(CustomerRequestDTO customerRequestDTO){
        return modelMapper.map(customerRequestDTO, Customer.class);
    }
}
