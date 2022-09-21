package com.example.Dva.Rep;

import com.example.Dva.models.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository  extends CrudRepository<User, Long> {
User findByUsername(String username);
}
