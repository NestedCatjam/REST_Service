package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name ="posts")
public class Post implements ControlCognizantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "postno")
    private UUID id;
    @Column(name = "content")
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User User;
    public Post(User user, UUID id) {
        this.User = user;
        this.id = id;
    }
    public Post() {

    }
}
