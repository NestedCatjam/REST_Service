package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ChatLog;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    public List<Post> findPostsByChatlogId(long id);
}
