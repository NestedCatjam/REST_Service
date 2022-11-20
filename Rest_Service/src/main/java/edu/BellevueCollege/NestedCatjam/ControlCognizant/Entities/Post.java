package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name ="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "postno")
    private Integer id;

    @Column(name = "Post_Content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    // @JsonIgnore might cause an infinite loop if removed.  I'm not sure.
    @JsonIgnore
    @ToString.Exclude
    private User user;
}
