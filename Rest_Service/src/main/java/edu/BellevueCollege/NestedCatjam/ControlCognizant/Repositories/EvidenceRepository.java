package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
    // id is lowercase on purpose because spring will infer the column name from the method name
    Evidence findEvidenceById(long id);
}
