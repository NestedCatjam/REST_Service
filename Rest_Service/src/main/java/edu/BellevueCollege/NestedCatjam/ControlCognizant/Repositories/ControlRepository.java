package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlRepository extends JpaRepository<Control, Integer> {

}
