package com.learningSpring.rest.webservices.restfulwebservices.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static final List<User> PEOPLE = new ArrayList<>();

    private static int usersCount;
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
    public User findUser(int id) {
        for(User User : PEOPLE) {
            if(User.getId() == id) {
                return User;
            }
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = PEOPLE.iterator();
        while(iterator.hasNext()) {
            User User = iterator.next();
            if(User.getId() == id) {
                iterator.remove();
                return User;
            }
        }
        return null;
    }
}
