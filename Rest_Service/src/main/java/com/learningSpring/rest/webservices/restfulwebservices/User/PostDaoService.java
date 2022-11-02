package com.learningSpring.rest.webservices.restfulwebservices.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PostDaoService {
    private static final List<Post> POSTS = new ArrayList<>();
    private static int postsCount;
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
    public Post findPost(int id) {
        for(Post Post : POSTS) {
            if(Post.getId() == id) {
                return Post;
            }
        }
        return null;
    }

    public Post deleteById(int id) {
        Iterator<Post> iterator = POSTS.iterator();
        while(iterator.hasNext()) {
            Post Post = iterator.next();
            if(Post.getId() == id) {
                iterator.remove();
                return Post;
            }
        }
        return null;
    }
}
