package com.learningSpring.rest.webservices.restfulwebservices.Repositories;

import com.learningSpring.rest.webservices.restfulwebservices.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
