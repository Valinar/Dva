package com.example.Dva.Rep;

import com.example.Dva.models.Awards;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AwardsRepository extends CrudRepository<Awards, Long> {
    Awards findByName(String name);
    List<Awards> findAll();
}
