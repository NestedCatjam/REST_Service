package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.NistControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NistRepository extends JpaRepository<NistControl, Long> {
    public NistControl findById(long id);
    public List<NistControl> findAllBySatisfied(boolean isSatisfied);
    public List<NistControl> findAllByHitrustMapping(String hitrustMapping);
    public List<NistControl> findAllByControlCategory(String controlCategory);
    public List<NistControl> findAllByControlFunction(String controlFunction);
}
