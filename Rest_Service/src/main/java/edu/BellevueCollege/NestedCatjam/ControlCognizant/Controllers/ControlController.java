package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils.ControlDaoUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ControlController {
    public ControlDaoUtil controlDaoUtil;

    public ControlController(ControlDaoUtil controlDaoUtil) {
        this.controlDaoUtil = controlDaoUtil;
    }
    @GetMapping("/control")
    public List<Control> getAllControls() {
        return controlDaoUtil.findAll();
    }
    @GetMapping("/control/{id}")
    public Control getControl(@PathVariable UUID id) {
        return controlDaoUtil.findControl(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
    }
    @PutMapping("/control")
    public Control createControl(Control control) {
        return controlDaoUtil.save(control);
    }
    @DeleteMapping("/control/{id}")
    public Control deleteControl(@PathVariable UUID id) {
        return controlDaoUtil.deleteById(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
    }
}
