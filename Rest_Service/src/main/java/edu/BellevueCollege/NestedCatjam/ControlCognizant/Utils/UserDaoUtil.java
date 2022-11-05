package edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ControlCognizantEntity;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoUtil {
    private static final List<User> PEOPLE = new ArrayList<>();

    private static long usersCount;
    public List<User> findAll() {
        return PEOPLE;
    }
    public User save(User User) {
        if (User.getId() == 0) {
            User.setId(usersCount++);
        }
        PEOPLE.add(User);
        return User;
    }
    public Optional<User> findUser(long id) {
        return ControlCognizantEntity.findEntity(id, PEOPLE);
    }

    public Optional<User> deleteById(int id) {
        return ControlCognizantEntity.deleteEntityById(id, PEOPLE);
    }
}
