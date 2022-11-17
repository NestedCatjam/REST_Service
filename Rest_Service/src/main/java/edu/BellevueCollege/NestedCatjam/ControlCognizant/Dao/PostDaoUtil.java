package edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostDaoUtil {
    private static final List<Post> POSTS = new ArrayList<>();
    private static int postsCount;
    public List<Post> findAll() {
        return POSTS;
    }
    public void save(Post post) {
        Integer temp = post.getId();
        if (temp.equals(null)) {
            post.setId(postsCount + 1);
        }
        POSTS.add(post);
        postsCount++;
    }

    public void deleteById(int id) {
        for (int i = 0; i < POSTS.size(); i++) {
            if (POSTS.get(i).getId() == id) {
                POSTS.remove(i);
                return;
            }
        }
        postsCount--;
    }
    public void updateByID(Post post) {
        for (int i = 0; i < POSTS.size(); i++) {
            if (POSTS.get(i).getId() == post.getId()) {
                POSTS.set(i, post);
                break;
            }
        }
    }
}
