package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.HitrustControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HitrustRepository extends JpaRepository<HitrustControl, Long> {
    HitrustControl findById(long id);
    List<HitrustControl> findAllBySatisfied(boolean isSatisfied);
    HitrustControl findHitrustControlByNistMapping(String hitrustMapping);
    List<HitrustControl> findAllByControlCategory(String controlCategory);
    List<HitrustControl> findAllByControlFunction(String controlFunction);
    List<HitrustControl> findAllByNistMapping(String nistMapping);
}