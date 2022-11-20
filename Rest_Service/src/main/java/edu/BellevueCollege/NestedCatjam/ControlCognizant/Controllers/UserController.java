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
        assert repository.findAll().size() > 0;
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable UUID id) {
        assert repository.findById(id).isPresent();
        return repository.findById(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable UUID id) throws UserNotFoundException {
        assert repository.findById(id).isPresent();
        repository.deleteById(id);
    }
    @PostMapping("/users")
    public void postUser(@Valid @RequestBody User User) {
        for (User user : repository.findAll()) {
            if (user.getId().equals(User.getId())) {
                throw new IllegalArgumentException("User with id " + User.getId() + " already exists.");
            }
        }
        repository.save(User);
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
