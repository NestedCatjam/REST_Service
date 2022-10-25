package com.learningSpring.rest.webservices.restfulwebservices.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<person, Integer> {

}
