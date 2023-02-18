package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping("/api/users/get-all-users")
    public List<User> retrieveAllUsers() {
        try {
            return repository.findAll();
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

    @GetMapping("/api/users/user/{id}")
    public String getUserById(@PathVariable long id) {
        try {
            for (User user : repository.findAll()) {
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

    @PostMapping(value = "/users/{id}/roles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void assignRole(@PathVariable String id, @RequestBody List<String> role) throws Auth0Exception {
        userManagementService.assignRole(id, role);
    }

    @DeleteMapping("/api/users/user/{id}")
    public void deleteUser(@PathVariable long id) throws UserNotFoundException {
        try {
            for (User user : repository.findAll()) {
                if (user.getId() == id) {
                    repository.deleteById(id);
                    return;
                }
            }
            throw new UserNotFoundException("user with id " + id + " not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PostMapping("/api/users/post-user")
    public String postUser(@Valid @RequestBody User user) {
        try {
            repository.save(user);
            return "saved user with id: " + user.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed to save user";
    }
    @PutMapping("/api/users/user/{id}")
    public String updateUser(@RequestBody User user) {
        for(User u : repository.findAll()) {
            String tempID = String.valueOf(user.getId());
            if(u.getId() == u.getId()) {
                u.setEmail(user.getEmail());
                u.setUserName(user.getUserName());
                u.setId(user.getId());
                u.setEnabled(user.isEnabled());
                u.setPassword(user.getPassword());
                u.setEmail(user.getEmail());
                u.setLast_name(user.getLast_name());
                u.setFirst_name(user.getFirst_name());
                u.setChatLogs(user.getChatLogs());
                u.setLocked(user.isLocked());
                u.setRole(user.getRole());
                u.setCredentialsExpired(user.isCredentialsExpired());
                return "updated user with id: " + u.getId() + " to " + String.valueOf(user.getId());
            } else {
                return "failed to update user with id: " + u.getId();
            }
        }
        return "failed to update user";
    }
}
