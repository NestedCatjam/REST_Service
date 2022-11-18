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
        user.setUuid(UUID.randomUUID());
        user.setId((long) (PEOPLE.size() + 1));
        PEOPLE.add(user);
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
        // loop through people and delete a user by id, decrement every other users id
        for (int i = 0; i < PEOPLE.size(); i++) {
            if (PEOPLE.get(i).getId().equals(id)) {
                PEOPLE.remove(i);
                for (int j = i; j < PEOPLE.size(); j++) {
                    PEOPLE.get(j).setId(PEOPLE.get(j).getId() - 1);
                }
            }
        }
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
