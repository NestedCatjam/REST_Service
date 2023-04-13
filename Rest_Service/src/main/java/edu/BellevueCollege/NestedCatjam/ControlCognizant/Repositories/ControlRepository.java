package edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControlRepository extends JpaRepository<Control, Long> {
    public Control findByName(String name);
}