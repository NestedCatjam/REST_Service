package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "compliance_evidence")
public class ComplianceEvidence{
    @Id
    @GeneratedValue
    @Column(name = "id_no", nullable = false)
    @Type(type = "uuid-char")
    private UUID id;

    @Lob
    private Blob evidence;

    @ManyToMany
    @JsonIgnore
    private List<Control> controlsImplemented;

    @ManyToOne
    @JsonIgnore
    private User user;
}
