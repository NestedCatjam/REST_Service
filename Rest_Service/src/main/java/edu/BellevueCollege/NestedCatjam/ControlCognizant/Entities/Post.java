package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
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

    @ManyToOne
    @JoinColumn(name = "chatlog_id")
    private ChatLog chatlog;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {
        date = LocalDate.now();
    }
}
