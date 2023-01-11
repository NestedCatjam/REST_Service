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
    @GeneratedValue
    @Id
    @Column(nullable = false, updatable = false, name = "user_Id")
    @Type(type = "uuid-char")
    private UUID id;

    @Size(min = 2, message= "Name must be longer than 2 characters")
    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "user_Email")
    @Email
    private String email;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<Post> posts;

    @ToString.Exclude
    @Column(name = "user_Password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "user_Name")
    private String userName;
    @Column(name = "user_Enabled")
    private boolean enabled;
    @Column(name = "user_Locked")
    private boolean locked;
    @Column(name = "credentials")
    private boolean credentialsExpired;
 }
