package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils.PostDaoUtil;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils.UserDaoUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
public class PostController {
    private PostDaoUtil postDaoService;
    private UserDaoUtil userDaoService;

    public PostController(PostDaoUtil postDaoService) {
        this.postDaoService = postDaoService;
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable UUID id) {
        User user = userDaoService.findUser(id).orElseThrow(() -> new UserNotFoundException("id-" + id));

        return user.getPosts();
    }
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable UUID id, @RequestBody Post post) {
        User user = userDaoService.findUser(id).orElseThrow(() -> new UserNotFoundException("id-" + id));
        Post savedPost = postDaoService.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/users/{id}/posts")
    public ResponseEntity<Object> updatePost(@PathVariable UUID id, @RequestBody Post post) {
        User user = userDaoService.findUser(id).orElseThrow(() -> new UserNotFoundException("id-" + id));

        Post savedPost = postDaoService.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/users/{id}/posts")
    public void deletePost(@PathVariable UUID id) {
        User user = userDaoService.findUser(id).orElseThrow(() -> new UserNotFoundException("id-" + id));
        postDaoService.deleteById(id);
    }
}
