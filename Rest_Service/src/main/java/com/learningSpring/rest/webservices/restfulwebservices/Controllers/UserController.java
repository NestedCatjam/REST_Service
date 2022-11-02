package com.learningSpring.rest.webservices.restfulwebservices.Controllers;

import com.learningSpring.rest.webservices.restfulwebservices.Exceptions.UserNotFoundException;
import com.learningSpring.rest.webservices.restfulwebservices.User.User;
import com.learningSpring.rest.webservices.restfulwebservices.User.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) throws UserNotFoundException {
        User User = service.findUser(id);

        if (User == null) {
            throw new UserNotFoundException("id =" + id);
        }
        EntityModel<User> model = EntityModel.of(User);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) throws UserNotFoundException {
        User User = service.deleteById(id);
        if (User == null) {
            throw new UserNotFoundException("id =" + id);
        }
    }
    @PostMapping("/users")
    public ResponseEntity<Object> postUser(@Valid @RequestBody User User) {
        User savedUser = service.save(User);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
