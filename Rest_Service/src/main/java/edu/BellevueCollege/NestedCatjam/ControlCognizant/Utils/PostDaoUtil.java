package edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ControlCognizantEntity;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PostDaoUtil {
    private static final List<Post> POSTS = new ArrayList<>();
    private static long postsCount;
    public List<Post> findAll() {
        return POSTS;
    }
    public Post save(Post Post) {
        if (Post.getId() == null) {
            Post.setId(UUID.randomUUID());
        }
        POSTS.add(Post);
        return Post;
    }
    public Optional<Post> findPost(UUID id) {
        return ControlCognizantEntity.findEntity(id, POSTS);
    }

    public Optional<Post> deleteById(UUID id) {
        return ControlCognizantEntity.deleteEntityById(id, POSTS);
    }
}
