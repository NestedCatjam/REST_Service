package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ComplianceEvidence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EvidenceRepository extends JpaRepository<ComplianceEvidence, UUID> {
    // id is lowercase on purpose because spring will infer the column name from the method name
    ComplianceEvidence findComplianceEvidenceByid(UUID id);
}
