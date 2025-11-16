package com.customerservice.Service;

import com.customerservice.dto.CustomerRequestDTO;
import com.customerservice.dto.CustomerResponseDTO;
import com.customerservice.entitie.Customer;
import com.customerservice.exception.CustomerNotFoundException;
import com.customerservice.exception.EmailAlreadyUsedException;
import com.customerservice.mapper.CustomerMapper;
import com.customerservice.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService{
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO requestDTO) throws EmailAlreadyUsedException {
        if(customerRepository.checkIfEmailExists(requestDTO.getEmail()))
            throw new EmailAlreadyUsedException(String.format("This email % is aleardy used", requestDTO.getEmail()));
        Customer customer = customerMapper.from(requestDTO);
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.from(savedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> listCustomer() {
        return customerRepository.findAll().stream().map(customerMapper::from).collect(Collectors.toList());
    }

    @Override
    public CustomerResponseDTO getCustomerById(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElse(null);
        if(customer == null) throw new CustomerNotFoundException(String.format("Customer Not Found: %s", id));
        return customerMapper.from(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO requestDTO) throws CustomerNotFoundException, EmailAlreadyUsedException {
        if (customerRepository.checkIfEmailExists(requestDTO.getEmail()))
            throw new EmailAlreadyUsedException(String.format("This email %s is already used",requestDTO.getEmail()));
        Customer customer = customerRepository.findById(requestDTO.getId()).orElse(null);
        if (customer==null) throw new CustomerNotFoundException(String.format("Customer Not Found :%s",requestDTO.getId()));
        if(requestDTO.getEmail()!=null) customer.setFirstname(requestDTO.getFirstName());
        if(requestDTO.getLastName()!=null) customer.setLastname(requestDTO.getLastName());
        if(requestDTO.getEmail()!=null) customer.setEmail(requestDTO.getEmail());
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.from(savedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer==null) throw new CustomerNotFoundException(String.format("Customer Not Found :%s",id));
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerResponseDTO> findCustomers(String keyWord) {
        List<Customer> customers = customerRepository.searchCustomers(keyWord);
        return customers.stream().map(customerMapper::from).collect(Collectors.toList());
    }

    @PostConstruct
    public void populateData() throws EmailAlreadyUsedException {
        for (int i = 1; i <= 5; i++) {
            CustomerRequestDTO customerRequestDTO=new CustomerRequestDTO(null,"First Name "+i,"Last Name "+i,"email"+i+"@gmail.com");
            CustomerResponseDTO customerResponseDTO = save(customerRequestDTO);
        }
    }
}
