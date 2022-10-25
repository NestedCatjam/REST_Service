package com.learningSpring.rest.webservices.restfulwebservices.User;

import com.learningSpring.rest.webservices.restfulwebservices.Exceptions.UserNotFoundException;
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
public class UserResource {
    @Autowired
    private UserDaoService service;

    @GetMapping("/users")
    public List<person> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<person> retrieveUser(@PathVariable int id) throws UserNotFoundException {
        person person = service.findUser(id).orElse(null);

        if (person == null) {
            throw new UserNotFoundException("id =" + id);
        }
        EntityModel<person> model = EntityModel.of(person);
        WebMvcLinkBuilder linkToUsers = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        model.add(linkToUsers.withRel("all-users"));
        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) throws UserNotFoundException {
        person person = service.deleteById(id);
        if (person == null) {
            throw new UserNotFoundException("id =" + id);
        }
    }
    @PostMapping("/users")
    public ResponseEntity<Object> postUser(@Valid @RequestBody person person) {
        person savedPerson = service.save(person);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPerson.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
