package edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class UserDaoUtil {
    private static List<User> PEOPLE = new ArrayList<>();

    private static long usersCount;
    public List<User> findAll() {
        return PEOPLE;
    }
    public User save(User User) {
        if (User.getId() == null) {
            User.setId(usersCount + 1);
        }
        PEOPLE.add(User);
        usersCount++;
        return User;
    }
    public Optional<User> findUser(Long id) {
            for (User user : PEOPLE) {
                if (user.getId().equals(id)) {
                    return Optional.of(user);
                }
            }
            return Optional.empty();
        }
    public Optional<User> deleteById(Long id) {
            Iterator<User> iterator = PEOPLE.iterator();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (user.getId().equals(id)) {
                    iterator.remove();
                    return Optional.of(user);
                }
            }
            return Optional.empty();
        }

    public void updateById(User user) {
        PEOPLE = findAll();
        if (user.getId() == null) {
            throw new UserNotFoundException("User must have an ID");
        }
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
