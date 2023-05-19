package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.NistControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NistRepository extends JpaRepository<NistControl, Long> {
    NistControl findById(long id);
    List<NistControl> findAllBySatisfied(boolean isSatisfied);
    List<NistControl> findAllByHitrustMapping(String hitrustMapping);
    List<NistControl> findAllByControlCategory(String controlCategory);
    List<NistControl> findAllByControlFunction(String controlFunction);
}
