package edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities;

import com.fasterxml.jackson.annotation.*;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao.UserDaoUtil;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Setter(AccessLevel.NONE)
    private User contributor;

    @Autowired
    @JsonIgnore
    @Transient
    private UserDaoUtil users;


    @JsonProperty("contributorId")
    public void setContributorById(UUID id) {
        contributor = users.findUser(id).orElseThrow(() -> new UserNotFoundException("id-" + id));
    }
}
