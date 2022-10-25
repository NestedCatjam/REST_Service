package com.learningSpring.rest.webservices.restfulwebservices.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class UserDaoService {
    private static final List<person> PEOPLE = new ArrayList<>();

    private static int usersCount = 3;
    public List<person> findAll() {
        return PEOPLE;
    }
    public person save(person person) {
        if (person.getId() == 0) {
            person.setId(usersCount++);
        }
        PEOPLE.add(person);
        return person;
    }
    public Optional<person> findUser(int id) {
        return PEOPLE.stream().filter(person -> person.getId() == id).findFirst();

    }

    public person deleteById(int id) {
        Iterator<person> iterator = PEOPLE.iterator();
        while(iterator.hasNext()) {
            person person = iterator.next();
            if(person.getId() == id) {
                iterator.remove();
                return person;
            }
        }
        return null;
    }
}
