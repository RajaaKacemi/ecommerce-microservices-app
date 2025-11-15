package com.customerservice.Service;

import com.customerservice.dto.CustomerRequestDTO;
import com.customerservice.dto.CustomerResponseDTO;
import com.customerservice.exception.CustomerNotFoundException;
import com.customerservice.exception.EmailAlreadyUsedException;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO save(CustomerRequestDTO request) throws EmailAlreadyUsedException;
    List<CustomerResponseDTO> listCustomer();
    CustomerResponseDTO getCustomerById(Long id) throws CustomerNotFoundException;
    CustomerResponseDTO update(CustomerRequestDTO request) throws CustomerNotFoundException, EmailAlreadyUsedException;
    void deleteCustomer(Long id) throws CustomerNotFoundException;
    List<CustomerResponseDTO> findCustomers(String keyWord);
}
