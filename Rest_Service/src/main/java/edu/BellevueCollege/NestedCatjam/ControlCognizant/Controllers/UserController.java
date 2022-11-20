package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao.UserDaoUtil;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.UserRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class UserController {
    @Autowired
    private UserDaoUtil service;

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable UUID id) {
        return repository.findById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable UUID id) throws UserNotFoundException {
        repository.deleteById(id);
    }
    @PostMapping("/users")
    public void postUser(@Valid @RequestBody User User) {
        repository.save(User);
    }
    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody User User) {
        repository.save(User);
    }
}
