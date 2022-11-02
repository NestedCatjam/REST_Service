package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idno")
    private Integer id;
    @Size(min = 2, message= "Name must be longer than 2 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    @Email
    private String email;

    @OneToMany(mappedBy = "User")
    @ToString.Exclude
    private List<Post> posts;
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public User() {

    }
 }
