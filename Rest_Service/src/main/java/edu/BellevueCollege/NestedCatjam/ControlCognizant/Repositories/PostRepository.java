package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;


import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findPostsByEvidence_IdAndEvidence_Implemented_Id(long evidenceID, UUID controlID);
}
