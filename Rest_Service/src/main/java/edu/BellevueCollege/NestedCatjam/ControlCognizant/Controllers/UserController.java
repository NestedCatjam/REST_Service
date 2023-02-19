package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import com.auth0.exception.Auth0Exception;
import com.auth0.json.mgmt.users.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.UserRepository;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.services.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @Autowired private UserManagementService userManagementService;

    @GetMapping("/api/users/get-all-users")
    public List<User> retrieveAllUsers() throws Auth0Exception {
        try {
            return userManagementService.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
    }



    @PostMapping("/api/users/post-user")
    public String postUser(@Valid @RequestBody com.auth0.json.mgmt.users.User user) throws Auth0Exception {
        try {
            userManagementService.addUser(user);
            return "saved user with id: " + user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to save user with the following error: " + e.getMessage();
        }
    }

    @GetMapping("/api/users/user/{id}")
    public String getUserById(@PathVariable long id) {
        try {
            for (final var user : repository.findAll()) {
                if (user.getId() == id) {
                    return "user with id " + id + " found: " + user.toString();
                }
            }
            throw new UserNotFoundException("user with id " + id + " not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping(value = "/api/users/user/{id}/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void assignRole(@PathVariable String id, @RequestBody List<String> role) throws Auth0Exception {
        userManagementService.assignRole(id, role);
    }

    @DeleteMapping("/api/users/user/{id}")
    public void deleteUser(@PathVariable String id) throws Auth0Exception {
        try {
            userManagementService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @PutMapping("/api/users/user/{id}")
    public void updateUser(@PathVariable String id, @RequestBody User user) throws Auth0Exception {
        userManagementService.update(id, user);
    }
}
