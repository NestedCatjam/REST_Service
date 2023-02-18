package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "Users")
public class User {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @Column(name = "user_id")
    private long id;

    @Size(min = 2, message= "Name must be longer than 2 characters")
    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "user_Email")
    @Email
    private String email;

    @ToString.Exclude
    @ManyToMany
    private List<ChatLog> chatLogs;

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
