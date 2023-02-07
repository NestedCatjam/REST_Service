package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "chatlog")
public class Chatlog {
    @Id
    @GeneratedValue
    @Column(name = "chatlogid")
    private long id;

    @OneToMany(mappedBy = "chatlog")
    private List<Post> posts;

    @OneToOne
    private Evidence evidence;
}
