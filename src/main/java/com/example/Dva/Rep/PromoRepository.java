package com.example.Dva.Rep;

import com.example.Dva.models.Customer;
import com.example.Dva.models.Promo;
import com.example.Dva.models.User;
import org.springframework.data.repository.CrudRepository;

public interface PromoRepository extends CrudRepository<Promo, Long> {
    Promo findByName(String name);
}
