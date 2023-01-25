package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.filter.UserFilter;
import com.auth0.exception.Auth0Exception;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController("/api/user")
public class UserController {
    @Autowired
    private UserRepository repository;

    private ManagementAPI managementAPI;
    @GetMapping("/users")
    public List<com.auth0.json.mgmt.users.User> retrieveAllUsers() throws Auth0Exception {
//        try {
//            return repository.findAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        var results= managementAPI.users().list(new UserFilter()).execute();

        return results.getItems();
    }

    @GetMapping("/users/{id}")
    public Optional<User> getUserById(@PathVariable UUID id) {
        try {
            return repository.findById(id).or(() -> {
                throw new UserNotFoundException("user with id " + id + " not found");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable UUID id) throws UserNotFoundException {
        try {
            for(User user : repository.findAll()) {
                if(user.getId().equals(id)) {
                    repository.delete(user);
                }
            } throw new UserNotFoundException("user with id " + id + " not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/users")
    public User postUser(@Valid @RequestBody User User) {
        try {
            for (User user : repository.findAll()) {
                if (user.getId().equals(User.getId())) {
                    throw new IllegalArgumentException("User with id " + User.getId() + " already exists.");
                }
                if (User.getUserName().equals(user.getUserName())) {
                    throw new IllegalArgumentException("User with username " + User.getUserName() + " already exists.");
                }
            }
            return repository.save(User);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User User) {
        try {
            for (User user : repository.findAll()) {
                if (user.getId().equals(User.getId())) {
                    return repository.save(User);
                }
            } throw new UserNotFoundException("User with id " + User.getId() + " not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
