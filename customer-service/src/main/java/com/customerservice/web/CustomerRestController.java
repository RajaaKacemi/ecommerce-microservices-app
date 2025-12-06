package com.customerservice.web;

import com.customerservice.Service.CustomerService;
import com.customerservice.dto.CustomerRequestDTO;
import com.customerservice.dto.CustomerResponseDTO;
import com.customerservice.exception.CustomerNotFoundException;
import com.customerservice.exception.EmailAlreadyUsedException;
import com.customerservice.exception.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("/api/customers")
public class CustomerRestController {
    private CustomerService customerService;

    @GetMapping()
    public List<CustomerResponseDTO> listCustomers(){
        return customerService.listCustomers();
    }
    @GetMapping("search")
    public List<CustomerResponseDTO> searchCustomers(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return customerService.findCustomers("%"+keyword+"%");
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id){
        try {
            CustomerResponseDTO customerById = customerService.getCustomerById(id);
            return ResponseEntity.ok(customerById);
        } catch (CustomerNotFoundException e) {
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }
    @PostMapping()
    public ResponseEntity<?> saveNewCustomer(@RequestBody CustomerRequestDTO request){
        try {
            CustomerResponseDTO customerResponseDTO = customerService.save(request);
            return ResponseEntity.ok(customerResponseDTO);
        } catch (EmailAlreadyUsedException e) {
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage())); }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerRequestDTO request, @PathVariable Long id){
        try {
            CustomerResponseDTO customerResponseDTO = customerService.update(request);
            return ResponseEntity.ok(customerResponseDTO);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id){
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorMessage(e.getMessage()));
        }
    }
}
