package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JoinColumn(name = "evidence_id")
    @JsonIgnore
    private Evidence evidence;


    @Column(name = "auth0_user_id")
    private String auth0UserID;

    public Post() {
        date = LocalDate.now();
    }
    public Post(String content, String auth0UserID) {
        this();
        this.content = content;
        this.auth0UserID = auth0UserID;

    }

    @JsonProperty
    public long getEvidenceID() {
        return evidence.getId();
    }
}
