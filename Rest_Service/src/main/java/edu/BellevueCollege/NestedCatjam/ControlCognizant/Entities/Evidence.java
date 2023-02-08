package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Data
public class Evidence {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
    private long id;

    @Column(name = "evidence_name")
    private String name;

    @Column(name = "evidence_description")
    private String description;

    @Column(name = "evidence_type")
    private String type;

    @Lob
    private Blob file;
}