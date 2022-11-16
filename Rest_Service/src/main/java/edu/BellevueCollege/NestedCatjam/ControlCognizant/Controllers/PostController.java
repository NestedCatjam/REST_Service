package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao.PostDaoUtil;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao.UserDaoUtil;
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
    public List<Post> retrieveAllPosts(@PathVariable Long id) {
        User user = userDaoService.findUser(id).orElseThrow(() -> new UserNotFoundException("id-" + id));

        return user.getPosts();
    }
    @GetMapping("/users/{id}/posts/{post_id}")
    public Post retrievePost(@PathVariable Long id, @PathVariable int post_id) {
        User user = userDaoService.findUser(id).orElseThrow(() -> new UserNotFoundException("id-" + id));

        return user.getPosts().stream().filter(post -> post.getId() == post_id).findFirst().orElseThrow(() -> new UserNotFoundException("id-" + id));
    }
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@RequestBody Post post) {
        postDaoService.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/users/{id}/posts/{post_id}")
    public void updatePost(@PathVariable Long id, @RequestBody Post post) {
        postDaoService.updateByID(id, post);

    }
    @DeleteMapping("/users/{id}/posts/{post_id}")
    public void deletePost(@PathVariable Long id, @PathVariable int post_id) {
        User user = userDaoService.findUser(id).orElseThrow(() -> new UserNotFoundException("id-" + id));
        postDaoService.deleteById(post_id);
    }
}
