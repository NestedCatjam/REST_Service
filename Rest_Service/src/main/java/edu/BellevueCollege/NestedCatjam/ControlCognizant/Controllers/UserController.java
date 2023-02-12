package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.client.mgmt.filter.UserFilter;
import com.auth0.exception.Auth0Exception;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.UserRepository;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.config.ApplicationProperties;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.services.UserManagementService;
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

    @Autowired
    private UserManagementService userManagementService;
    @Autowired
    private ApplicationProperties applicationProperties;
    @GetMapping("/users")
    public List<com.auth0.json.mgmt.users.User> retrieveAllUsers() throws Auth0Exception {
//        try {
//            return repository.findAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            return userManagementService.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
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
    public void deleteUser(@PathVariable String id) throws Auth0Exception {
        System.out.println(id);
        userManagementService.delete(id);
    }
    @PostMapping("/users")
    public com.auth0.json.mgmt.users.User postUser(@Valid @RequestBody com.auth0.json.mgmt.users.User user) throws Auth0Exception {
        System.out.println(user.getEmail().toString());
        return userManagementService.addUser(user);
    }
    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable String id, @RequestBody com.auth0.json.mgmt.users.User user) throws Auth0Exception {

        userManagementService.update(id, user);
    }
}
