package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "evidence")
public class Evidence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evidence_id")
    private long id;

    @Column(name = "evidence_name")
    private String name;

    @Column(name = "evidence_description")
    private String description;

    @Column(name = "evidence_type")
    private String type;

    @Column(name = "evidence_file")
    String base64;

    @Column(name = "contributor_auth0_id")
    private String contributorAuth0ID;

    @ManyToOne
    @JoinColumn(name = "implemented_control_id")
    private NistControl implemented;

    @Column(name = "chat_id")
    private String chatid;


    public Evidence() {
    }

    public Evidence(String name, String description, String type, String base64)  {
        this.name = name;
        this.description = description;
        this.type = type;
        this.base64 = base64;
    }
}