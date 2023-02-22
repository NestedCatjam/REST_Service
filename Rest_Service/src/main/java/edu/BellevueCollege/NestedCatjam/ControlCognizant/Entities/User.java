package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private long id;

    @Size(min = 2, message= "Name must be longer than 2 characters")
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_email")
    @Email
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_enabled")
    private boolean enabled;

    @Column(name = "user_locked")
    private boolean locked;

    @Column(name = "credentials")
    private boolean credentialsExpired;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> post;
}
