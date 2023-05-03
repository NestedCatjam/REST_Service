package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.HitrustControl;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.NistControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HitrustRepository extends JpaRepository<HitrustControl, Long> {
    public HitrustControl findById(long id);
    public List<HitrustControl> findAllBySatisfied(boolean isSatisfied);
    public HitrustControl findHitrustControlByNistMapping(String hitrustMapping);
    public List<HitrustControl> findAllByControlCategory(String controlCategory);
    public List<HitrustControl> findAllByControlFunction(String controlFunction);
}