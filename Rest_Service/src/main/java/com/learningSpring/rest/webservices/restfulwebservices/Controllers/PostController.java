package com.learningSpring.rest.webservices.restfulwebservices.Controllers;

import com.learningSpring.rest.webservices.restfulwebservices.Exceptions.UserNotFoundException;
import com.learningSpring.rest.webservices.restfulwebservices.Entities.Post;
import com.learningSpring.rest.webservices.restfulwebservices.Utils.PostDaoUtil;
import com.learningSpring.rest.webservices.restfulwebservices.Entities.User;
import com.learningSpring.rest.webservices.restfulwebservices.Utils.UserDaoUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {
    private PostDaoUtil postDaoService;
    private UserDaoUtil userDaoService;

    public PostController(PostDaoUtil postDaoService) {
        this.postDaoService = postDaoService;
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPosts(@PathVariable int id) {
        User user = userDaoService.findUser(id);
        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        return user.getPosts();
    }
    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
        User user = userDaoService.findUser(id);
        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        Post savedPost = postDaoService.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/users/{id}/posts")
    public ResponseEntity<Object> updatePost(@PathVariable int id, @RequestBody Post post) {
        User user = userDaoService.findUser(id);
        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        Post savedPost = postDaoService.save(post);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/users/{id}/posts")
    public void deletePost(@PathVariable int id) {
        User user = userDaoService.findUser(id);
        if(user == null) {
            throw new UserNotFoundException("id-" + id);
        }
        postDaoService.deleteById(id);
    }
}
