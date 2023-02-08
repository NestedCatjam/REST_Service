package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "chatlog")
public class ChatLog {
    @Id
    @GeneratedValue
    @Column(name = "chatlogid")
    private long id;

    @OneToMany(mappedBy = "log")
    private List<Post> posts;

    @OneToOne
    private Evidence evidence;
}