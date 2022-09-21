package com.example.Dva.Rep;

import com.example.Dva.models.Data;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DataRepository extends CrudRepository<Data, Long> {
    Data findByName(String name);

}

