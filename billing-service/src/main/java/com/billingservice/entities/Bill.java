package com.billingservice.entities;

import java.util.Date;
import java.util.List;

import com.billingservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

@Entity @Getter
@Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class Bill {
    @Id @GeneratedValue
    private Long id;
    private Date billingDate;
    private long customerId;
    @OneToMany(mappedBy = "bill")
    //Une facture contient plusieurs ProductItems
    private List<ProductItem> productsItems;
    @Transient
    //Indiquer a JPA que cette attribuer n'existe pas.
    private Customer customer;
}
