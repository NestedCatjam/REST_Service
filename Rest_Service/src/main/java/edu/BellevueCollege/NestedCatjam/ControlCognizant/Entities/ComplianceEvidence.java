package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "compliance_evidence")
public class ComplianceEvidence{
    @Id
    @Column(name = "id_no", nullable = false)
    private UUID id;

    @Lob
    private Blob evidence;

    @ManyToMany
    private List<Control> controlsImplemented;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contributor_id", nullable = false)

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("contributorId")
    private User contributor;
}
