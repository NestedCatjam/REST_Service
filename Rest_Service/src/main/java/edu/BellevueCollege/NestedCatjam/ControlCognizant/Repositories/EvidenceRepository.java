package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
    // id is lowercase on purpose because spring will infer the column name from the method name
    Evidence findEvidenceById(long id);
    List<Evidence> findEvidenceByImplemented_Id(long controlID);

    Evidence findEvidenceByIdAndImplemented_Id(long evidenceID, long controlID);
}
