package edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoUtil {
    private static final List<User> PEOPLE = new ArrayList<>();
    public List<User> findAll() {
        return PEOPLE;
    }
    public void save(User user) {
        user.setId(UUID.randomUUID());
        PEOPLE.add(user);
    }
    public Optional<User> findUser(UUID id) {
            for (User user : PEOPLE) {
                if (user.getId().equals(id)) {
                    return Optional.of(user);
                }
            }
            return Optional.empty();
        }
    public void deleteById(UUID id) {
        PEOPLE.removeIf(person -> Objects.equals(person.getId(), id));
    }

    public void updateById(User user) {
        for (User u : PEOPLE) {
            if (u.getId().equals(user.getId())) {
                u.setName(user.getName());
                u.setEmail(user.getEmail());
                u.setPosts(user.getPosts());
            }
        }
    }
}
