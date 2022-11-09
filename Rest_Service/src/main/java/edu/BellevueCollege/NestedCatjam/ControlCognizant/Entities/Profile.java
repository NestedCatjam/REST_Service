package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "profile")
public class Profile {
    UUID uuid = UUID.randomUUID();
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Column(name = "accountNonExpired", nullable = false)
    private boolean accountNonExpired;
    @Column(name = "credentialsNonExpired", nullable = false)
    private boolean credentialsNonExpired;
    @Column(name = "accountNonLocked", nullable = false)
    private boolean accountNonLocked;
    @Column(name = "role", nullable = false)
    private String role;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_idno", referencedColumnName = "id")
    private Profile profile;
    @OneToOne(mappedBy = "user")
    private User user;

}
