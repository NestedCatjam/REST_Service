package com.learningSpring.rest.webservices.restfulwebservices.User;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private person person;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUser(person person) {
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public person getUser() {
        return person;
    }
}
