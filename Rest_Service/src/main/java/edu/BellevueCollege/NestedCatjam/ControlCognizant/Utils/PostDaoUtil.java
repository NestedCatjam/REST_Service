package edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ControlCognizantEntity;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Component
public class PostDaoUtil {
    private static final List<Post> POSTS = new ArrayList<>();
    private static long postsCount;
    public List<Post> findAll() {
        return POSTS;
    }
    public Post save(Post Post) {
        if (Post.getId() == 0) {
            Post.setId(postsCount++);
        }
        POSTS.add(Post);
        return Post;
    }
    public Optional<Post> findPost(int id) {
        return ControlCognizantEntity.findEntity(id, POSTS);
    }

    public Optional<Post> deleteById(int id) {
        return ControlCognizantEntity.deleteEntityById(id, POSTS);
    }
}
