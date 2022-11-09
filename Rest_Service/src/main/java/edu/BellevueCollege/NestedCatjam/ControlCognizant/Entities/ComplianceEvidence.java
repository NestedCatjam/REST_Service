package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "compliance_evidence")
public class ComplianceEvidence implements ControlCognizantEntity {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Lob
    private Blob evidence;
    @ManyToMany
    private List<Control> controlsImplemented;

    @ManyToOne
    private User contributor;
}
