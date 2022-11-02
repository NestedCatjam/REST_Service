package com.learningSpring.rest.webservices.restfulwebservices.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name ="posts")
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
    private com.learningSpring.rest.webservices.restfulwebservices.Entities.User User;
    public Post(User user, int id) {
        this.User = user;
        this.id = id;
    }
    public Post() {

    }
}
