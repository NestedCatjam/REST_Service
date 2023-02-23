package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.ChatLogRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

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


    @Column(name = "auth0_user_id")
    private String auth0UserID;

    public Post() {
        date = LocalDate.now();
    }
    public Post(String content, String auth0UserID) {
        this.content = content;
        this.auth0UserID = auth0UserID;
    }
}
