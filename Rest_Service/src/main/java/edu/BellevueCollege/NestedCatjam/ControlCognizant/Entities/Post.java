package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "postid")
    private long id;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private LocalDate date;

    @ManyToOne // TODO
    private ChatLog chatlog;

    public Post() {
        date = LocalDate.now();
    }
}