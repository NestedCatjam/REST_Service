package edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoUtil {
    private static final List<User> PEOPLE = new ArrayList<>();

    private static long usersCount;
    public List<User> findAll() {
        return PEOPLE;
    }
    public void save(User User) {
        if (User.getId() == null) {
            User.setId(usersCount + 1);
        }
        PEOPLE.add(User);
        usersCount++;
    }
    public Optional<User> findUser(Long id) {
            for (User user : PEOPLE) {
                if (user.getId().equals(id)) {
                    return Optional.of(user);
                }
            }
            return Optional.empty();
        }
    public void deleteById(Long id) {
            PEOPLE.removeIf(user -> user.getId().equals(id));
            usersCount--;
    }

    public void updateById(User user) {
        for (User u : PEOPLE) {
            if (u.getId().equals(user.getId())) {
                u.setName(user.getName());
                u.setEmail(user.getEmail());
                u.setId(user.getId());
                u.setPosts(user.getPosts());
            }
        }
    }
}
