package edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostDaoUtil {
    private static final List<Post> POSTS = new ArrayList<>();
    public List<Post> findAll() {
        return POSTS;
    }
    public void save(Post post) {
        Integer temp = post.getId();
        if (temp.equals(null)) {
            post.setId(POSTS.size() + 1);
        }
        POSTS.add(post);
    }

    public void deleteById(int id) {
        for (int i = 0; i < POSTS.size(); i++) {
            if (POSTS.get(i).getId() == id) {
                POSTS.remove(i);
                // decrement all the ids after the deleted one
                for (int j = i; j < POSTS.size(); j++) {
                    POSTS.get(j).setId(POSTS.get(j).getId() - 1);
                }
            }
        }
    }
    public void updateByID(Post post) {
        for (int i = 0; i < POSTS.size(); i++) {
            if (POSTS.get(i).getId() == post.getId()) {
                post.setId(POSTS.get(i).getId());
                POSTS.set(i, post);
                break;
            }
        }
    }
}
