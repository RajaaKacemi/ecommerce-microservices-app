package com.customerservice.Service;

import com.customerservice.dto.CustomerRequestDTO;
import com.customerservice.dto.CustomerResponseDTO;
import com.customerservice.entitie.Customer;
import com.customerservice.exception.CustomerNotFoundException;
import com.customerservice.exception.EmailAlreadyUsedException;
import com.customerservice.mapper.CustomerMapper;
import com.customerservice.repository.CustomerRepository;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO request) throws EmailAlreadyUsedException {
        if(customerRepository.checkIfEmailExists(request.getEmail()))
            throw new EmailAlreadyUsedException(String.format("This email % is aleardy used", request.getEmail()));
        Customer customer = customerMapper.from(request);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.from(savedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomer() {
        return List.of();
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO request) throws CustomerNotFoundException, EmailAlreadyUsedException {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException {

    }

    @Override
    public List<CustomerResponseDTO> findCustomers(String keyWord) {
        return List.of();
    }
}
