package com.learningSpring.rest.webservices.restfulwebservices.pawn;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Pawn {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, message= "Name must be longer than 2 characters")
    private String name;
    @Past
    private Date birthDate;

    @OneToMany(mappedBy = "pawn")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Pawn(int id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Pawn() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
