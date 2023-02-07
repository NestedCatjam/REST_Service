package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("/api/user")
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

    @DeleteMapping("/users/{id}")
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
    @PostMapping("/users")
    public String postUser(@Valid @RequestBody User user) {
        try {
            repository.save(user);
            return "saved user with id: " + user.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "failed to save user";
    }
    @PutMapping("/users/{id}")
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
                u.setChatlogs(user.getChatlogs());
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
