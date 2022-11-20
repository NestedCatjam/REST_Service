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
    private UserRepository repository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable UUID id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable UUID id) throws UserNotFoundException {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/users")
    public void postUser(@Valid @RequestBody User User) {
        try {
            for (User user : repository.findAll()) {
                if (user.getId().equals(User.getId())) {
                    throw new IllegalArgumentException("User with id " + User.getId() + " already exists.");
                }
            }
            repository.save(User);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }
    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody User User) {
        try {
            for (User user : repository.findAll()) {
                if (user.getId().equals(User.getId())) {
                    repository.save(User);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
