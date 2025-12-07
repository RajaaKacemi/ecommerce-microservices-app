package com.billingservice.web;

import com.billingservice.entities.Bill;
import com.billingservice.feign.CustomerServiceRestClient;
import com.billingservice.feign.InventoryServiceRestClient;
import com.billingservice.model.Customer;
import com.billingservice.repository.BillRepository;
import com.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BillRestController {
    @Autowired
    private BillRepository  billRepository;
    @Autowired
    private CustomerServiceRestClient customerServiceRestClient;
    @Autowired
    private InventoryServiceRestClient inventoryServiceRestClient;

    @GetMapping("/bills/{id}")
    public Bill getBillById(@PathVariable Long id){
        Bill bill = billRepository.findById(id).get();
        Customer customer = customerServiceRestClient.findCustomerById(bill.getCustomerId());
        bill.setCustomer(customer);
        bill.getProductsItems().forEach(
                productItem -> {
                   productItem.setProduct(inventoryServiceRestClient.getProductById(productItem.getProductId()));
                });
        return bill;
    }
}
