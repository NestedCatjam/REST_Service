package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false, name = "user_Id")
    @Type(type = "uuid-char")
    private UUID id;

    @Size(min = 2, message= "Name must be longer than 2 characters")
    @Column(name = "user_name")
    private String name;

    @Column(name = "user_email")
    @Email
    private String email;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Post> posts;
 }
