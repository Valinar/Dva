package com.example.Dva.Rep;

import com.example.Dva.models.Customer;
import com.example.Dva.models.User;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByName(String name);
}
