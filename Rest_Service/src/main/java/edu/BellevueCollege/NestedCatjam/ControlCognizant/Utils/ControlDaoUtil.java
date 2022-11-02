package edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Component
public class ControlDaoUtil {
    private static final List<Control> CONTROL_LIST = new ArrayList<>();
    private static int controlCount;
    public List<Control> findAll() {
        return CONTROL_LIST;
    }
    public Control save(Control Control) {
        if (Control.getId() == 0) {
            Control.setId((long) controlCount++);
        }
        CONTROL_LIST.add(Control);
        return Control;
    }
    public Control findControl(Long id) {
        for(Control Control : CONTROL_LIST) {
            if(Objects.equals(Control.getId(), id)) {
                return Control;
            }
        }
        return null;
    }

    public Control deleteById(Long id) {
        for(Control Control : CONTROL_LIST) {
            if(Objects.equals(Control.getId(), id)) {
                CONTROL_LIST.remove(Control);
                return Control;
            }
        }
        return null;
    }


}
