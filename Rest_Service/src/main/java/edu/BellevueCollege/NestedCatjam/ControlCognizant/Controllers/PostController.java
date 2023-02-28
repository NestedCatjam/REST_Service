package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ChatLogNotFoundException;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.PostRepository;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
//@RequestMapping("/posts")
public class PostController {
    //@Autowired private ChatLogRepository chatLogRepository;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private EvidenceController evidenceController;

    @GetMapping("/api/controls/{controlID}/evidence/{evidenceID}/posts")
    @PreAuthorize("hasAuthority(\"chat:evidence\")")
    public List<Post> getPostsUnder(@PathVariable("controlID") UUID controlID, @PathVariable("evidenceID") long evidenceID) {
        return postRepository.findPostsByEvidence_IdAndEvidence_Implemented_Id(evidenceID, controlID);
    }

    @PostMapping("/api/controls/{controlID}/evidence/{evidenceID}/posts")
    @PreAuthorize("hasAuthority(\"chat:evidence\")")
    public Post postUnder(@PathVariable("controlID") UUID controlID, @PathVariable("evidenceID") long evidenceID, Authentication authentication, @RequestBody String text) {
        final var post = new Post();
        post.setAuth0UserID(authentication.getName());
        post.setContent(text);
        post.setEvidence(evidenceController.getEvidenceForById(authentication, controlID, evidenceID));
        return postRepository.save(post);
    }
}

