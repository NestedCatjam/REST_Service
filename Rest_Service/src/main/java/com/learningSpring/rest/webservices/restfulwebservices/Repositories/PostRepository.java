package com.learningSpring.rest.webservices.restfulwebservices.Repositories;

import com.learningSpring.rest.webservices.restfulwebservices.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
