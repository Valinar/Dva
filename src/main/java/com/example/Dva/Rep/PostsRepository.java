package com.example.Dva.Rep;

import com.example.Dva.models.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends CrudRepository<Posts, Long> {
 Posts findByName(String name);
}
