package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
    // id is lowercase on purpose because spring will infer the column name from the method name
    Evidence findEvidenceById(long id);
    List<Evidence> findEvidenceByImplemented_Id(UUID controlID);

    Evidence findEvidenceByIdAndImplemented_Id(long evidenceID, UUID controlID);

    List<Evidence> findEvidencesByImplemented_IdAndContributorAuth0ID(UUID controlID, String contributorAuth0ID);
    Evidence findEvidenceByIdAndImplemented_IdAndContributorAuth0ID(long evidenceID, UUID controlID, String contributorAuth0ID);
}
