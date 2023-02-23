package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ChatLogNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.ChatLogRepository;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.PostRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired private ChatLogRepository chatLogRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{chatlogID}")
    public ResponseEntity<List<Post>> getAllPosts(@PathVariable("chatlogID") long chatlogID) {
        List<Post> posts = postRepository.findPostsByChatlogId(chatlogID);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<Post> getPostById(@PathVariable("id") long id) {
//        Optional<Post> post = postRepository.findById(id);
//        if (post.isPresent()) {
//            return new ResponseEntity<>(post.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

    @PostMapping("/{chatlogID}")
    public ResponseEntity<Post> createPost(@PathVariable("chatlogID") long id, @RequestBody Post post) {
        final var chatlog = chatLogRepository.findById(id).orElseThrow(() -> new ChatLogNotFoundException());
        post.setChatlog(chatlog);
        Post newPost = postRepository.save(post);

        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@RequestHeader String webToken, @PathVariable("id") long id, @RequestBody Post post) {
//        Optional<Post> postData = postRepository.findById(id);
//        if (postData.isPresent()) {
//            Post existingPost = postData.get();
//            existingPost.setContent(post.getContent());
//            existingPost.setDate(post.getDate());
//            existingPost.setChatlog(post.getChatlog());
//            existingPost.setAuth0UserID(post.getAuth0UserID());
//            postRepository.save(existingPost);
//            return new ResponseEntity<>(existingPost, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }

        throw new NotImplementedException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") long id) {
//        try {
//            postRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        //}
        throw new NotImplementedException();
    }
}

