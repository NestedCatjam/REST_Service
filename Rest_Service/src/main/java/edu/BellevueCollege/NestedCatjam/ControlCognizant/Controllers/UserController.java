package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao.UserDaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
public class UserController {
    @Autowired
    private UserDaoUtil service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    // get user by id
    public Optional<User> getUserById(@PathVariable Long id) {
        return service.findUser(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        service.deleteById(id);
    }
    @PostMapping("/users")
    public void postUser(@Valid @RequestBody User User) {
        service.save(User);
    }
    @PutMapping("/users/{id}")
    public void updateUser(@RequestBody User User) {
        service.updateById(User);
    }
}
