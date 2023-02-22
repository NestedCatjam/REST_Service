package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Data
@Table(name = "chatlog")
public class ChatLog {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "chatlog_id")
    private long id;

    @OneToMany(mappedBy = "chatlog", cascade = CascadeType.ALL)
    private ArrayList<Post> posts = new ArrayList<>();

    @OneToOne(mappedBy = "chatlog", cascade = CascadeType.ALL)
    private Evidence evidence;
}
