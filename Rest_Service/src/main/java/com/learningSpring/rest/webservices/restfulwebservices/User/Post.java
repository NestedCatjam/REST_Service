package com.learningSpring.rest.webservices.restfulwebservices.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.management.ConstructorParameters;
import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "postno")
    private Integer id;
    @Column(name = "content")
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @ToString.Exclude
    private User User;
    public Post(User user, int id) {
        this.User = user;
        this.id = id;
    }
    public Post() {

    }
}
