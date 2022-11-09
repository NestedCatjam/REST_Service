package edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ControlCognizantEntity;
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
    public User save(User User) {
        if (User.getId() == null) {
            User.setId(UUID.randomUUID());
        }
        PEOPLE.add(User);
        return User;
    }
    public Optional<User> findUser(UUID id) {
        return ControlCognizantEntity.findEntity(id, PEOPLE);
    }

    public Optional<User> deleteById(UUID id) {
        return ControlCognizantEntity.deleteEntityById(id, PEOPLE);
    }
}
