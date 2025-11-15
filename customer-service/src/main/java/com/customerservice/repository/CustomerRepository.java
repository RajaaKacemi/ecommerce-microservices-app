package com.customerservice.repository;

import com.customerservice.entitie.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("Select c from Customer c where (c.firstname like :kw)" + "or (c.lastname like :kw) or (c.email like :kw)")
    List<Customer> searchCustomers(@Param("kw") String kw);

    @Query("select case when count(c)>0 then true else false END from Customer c where c.email=?1")
    Boolean checkIfEmailExists(String email);
    List<Customer> findByFirstnameContains(String fn);
    List<Customer> findByLastnameContainsOrLastnameContains(String fn, String ln);
}
