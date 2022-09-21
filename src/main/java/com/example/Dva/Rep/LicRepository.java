package com.example.Dva.Rep;

import com.example.Dva.models.Awardlicens;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LicRepository extends CrudRepository<Awardlicens, Long> {
    List<Awardlicens> findAll();
    Awardlicens findByNumber(String number);
}
