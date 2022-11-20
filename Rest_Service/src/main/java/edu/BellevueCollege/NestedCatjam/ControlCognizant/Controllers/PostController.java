package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.PostNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.PostRepository;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable UUID id) {
        try {
            for (User user : userRepository.findAll()) {
                if (user.getId().equals(id)) {
                    return user.getPosts();
                }
            } throw new UserNotFoundException("user with id " + id + " not found");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/users/{id}/posts/{post_id}")
    public Post retrievePost(@PathVariable UUID id, @PathVariable int post_id) {
        try {
            for (User user : userRepository.findAll()) {
                if (user.getId().equals(id)) {
                    for (Post post : user.getPosts()) {
                        if (post.getId() == post_id) {
                            return post;
                        }
                    } throw new PostNotFoundException("post with id " + post_id + " not found");
                }
            } throw new UserNotFoundException("user with id " + id + " not found");
        } catch (UserNotFoundException | PostNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable UUID id, @RequestBody Post post) {
        try {
            for (User user : userRepository.findAll()) {
                if (user.getId().equals(id)) {
                    post.setUser(user);
                    postRepository.save(post);
                    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
                    return ResponseEntity.created(location).build();
                }
            } throw new UserNotFoundException("user with id " + id + " not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PutMapping("/users/{id}/posts/{post_id}")
    public void updatePost(@PathVariable UUID id, @RequestBody Post post) {
        try {
            for (User user : userRepository.findAll()) {
                if (user.getId().equals(id)) {
                    for (Post postFromDb : user.getPosts()) {
                        if (postFromDb.getId().equals(post.getId())) {
                            post.setUser(user);
                            postRepository.save(post);
                        }
                    } throw new PostNotFoundException("Post with id " + post.getId() + " not found");
                }
            } throw new UserNotFoundException("User with id " + id + " not found");
        } catch (UserNotFoundException | PostNotFoundException e) {
            e.printStackTrace();
        }
    }
    @DeleteMapping("/users/{id}/posts/{post_id}")
    public void deletePost(@PathVariable UUID id, @PathVariable int post_id) {
        try {
            for (User user : userRepository.findAll()) {
                if (user.getId().equals(id)) {
                    for (Post postFromDb : user.getPosts()) {
                        if (postFromDb.getId().equals(user.getPosts().get(post_id).getId())) {
                            postRepository.delete(postFromDb);
                        }
                    } throw new PostNotFoundException("Post with id " + user.getPosts().get(post_id).getId() + " not found");
                }
            } throw new UserNotFoundException("User with id " + id + " not found");
        } catch (UserNotFoundException | PostNotFoundException e) {
            e.printStackTrace();
        }
    }
}
