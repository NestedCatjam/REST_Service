package edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ControlCognizantEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Component
public class ControlDaoUtil {
    private static final List<Control> CONTROLS = new ArrayList<>();
    private static int controlCount;
    public List<Control> findAll() {
        return CONTROLS;
    }
    public Control save(Control Control) {
        if (Control.getId() == 0) {
            Control.setId((long) controlCount++);
        }
        CONTROLS.add(Control);
        return Control;
    }
    public Optional<Control> findControl(long id) {
        return ControlCognizantEntity.findEntity(id, CONTROLS);
    }

    public Optional<Control> deleteById(long id) {
        return ControlCognizantEntity.deleteEntityById(id, CONTROLS);
    }


}
