package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao.PostDaoUtil;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao.UserDaoUtil;
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
        assert userRepository.findById(id).isPresent();
        return userRepository.findById(id).get().getPosts();
    }
    @GetMapping("/users/{id}/posts/{post_id}")
    public Post retrievePost(@PathVariable UUID id, @PathVariable int post_id) {
        assert userRepository.findById(id).isPresent();
        return userRepository.findById(id).get().getPosts().get(post_id);
    }
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable UUID id, @RequestBody Post post) {
        assert userRepository.findById(id).isPresent();
        User user = userRepository.findById(id).get();
        post.setUser(user);
        postRepository.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/users/{id}/posts/{post_id}")
    public void updatePost(@PathVariable UUID id, @RequestBody Post post) {
        assert userRepository.findById(id).isPresent();
        postRepository.save(post);
    }
    @DeleteMapping("/users/{id}/posts/{post_id}")
    public void deletePost(@PathVariable UUID id, @PathVariable int post_id) {
        assert userRepository.findById(id).isPresent();
        assert userRepository.findById(id).get().getPosts().get(post_id) != null;
        postRepository.delete(userRepository.findById(id).get().getPosts().get(post_id));
    }
}
