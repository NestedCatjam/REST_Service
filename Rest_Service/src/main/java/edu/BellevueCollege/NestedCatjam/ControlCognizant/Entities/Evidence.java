package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@Entity
@Data
public class Evidence {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE)
    @Column(name = "evidence_id")
    private long id;

    @Column(name = "evidence_name")
    private String name;

    @Column(name = "evidence_description")
    private String description;

    @Column(name = "evidence_type")
    private String type;

    @Column(name = "evidence_file")
    String base64;

    @Column(name = "contributor_auth0_id")
    private String contributorAuth0ID;

    @ManyToOne
    @JoinColumn(name = "implemented_control_id")
    private Control implemented;
    public Evidence() {
    }

    public Evidence(String name, String description, String type, String base64)  {
        this.name = name;
        this.description = description;
        this.type = type;
        this.base64 = base64;
    }

    @OneToMany(mappedBy = "evidence")
    private Collection<Post> posts;

    public Collection<Post> getPosts() {
        return posts;
    }

    public void setPosts(Collection<Post> posts) {
        this.posts = posts;
    }
}