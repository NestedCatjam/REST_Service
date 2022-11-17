package edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class ControlDaoUtil {
    private static final List<Control> CONTROLS = new ArrayList<>();
    private static int controlCount;
    public List<Control> findAll() {
        return CONTROLS;
    }
    public Control save(Control control) {
        if (control.getId() == null) {
            control.setId(UUID.randomUUID());
        }
        CONTROLS.add(control);
        controlCount++;
        return control;
    }
    public Optional<Control> findControl(UUID id) {
        for (Control control : CONTROLS) {
            if (control.getId().equals(id)) {
                return Optional.of(control);
            }
        }
        return Optional.empty();
    }

    public void deleteById(UUID id) {
        Iterator<Control> iterator = CONTROLS.iterator();
        while (iterator.hasNext()) {
            Control control = iterator.next();
            if (control.getId().equals(id)) {
                iterator.remove();
                break;
            }
        }
        controlCount--;
    }

    public void updateById(UUID id, Control control) {
        if (control.getId() == null) {
            throw new IllegalArgumentException("Control must have an ID");
        }
        for (Control control1 : CONTROLS) {
            if (control1.getId().equals(id)) {
                control1.setName(control.getName());
                control1.setDescription(control.getDescription());
                control1.setType(control.getType());
                control1.setId(control.getId());
                control1.setMapping(control.getMapping());
            }
        }
    }
}
