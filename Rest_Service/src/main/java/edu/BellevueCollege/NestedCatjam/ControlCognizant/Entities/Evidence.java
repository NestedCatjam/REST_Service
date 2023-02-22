package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;
import java.io.IOException;
import java.sql.SQLException;

@Entity
@Data
public class Evidence {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE)
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

    @OneToOne(mappedBy = "evidence", cascade = CascadeType.ALL)
    private ChatLog chatlog;

    public Evidence() {
    }

    public Evidence(String name, String description, String type, String base64) throws SQLException, IOException {
        this.name = name;
        this.description = description;
        this.type = type;
        this.base64 = base64;
    }
}