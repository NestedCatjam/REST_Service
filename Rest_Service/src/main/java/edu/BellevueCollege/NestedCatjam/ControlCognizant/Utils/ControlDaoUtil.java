package edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ControlCognizantEntity;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class ControlDaoUtil {
    private static final List<Control> CONTROLS = new ArrayList<>();
    private static int controlCount;
    public List<Control> findAll() {
        return CONTROLS;
    }
    public Control save(Control Control) {
        return ControlCognizantEntity.save(Control, CONTROLS);
    }
    public Optional<Control> findControl(UUID id) {
        return ControlCognizantEntity.findEntity(id, CONTROLS);
    }

    public Optional<Control> deleteById(UUID id) {
        return ControlCognizantEntity.deleteEntityById(id, CONTROLS);
    }


}
