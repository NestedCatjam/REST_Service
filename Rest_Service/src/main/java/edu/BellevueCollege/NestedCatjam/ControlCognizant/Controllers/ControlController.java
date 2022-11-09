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

    /**
     * Gets all the controls. Maps to HTTP get method for "/control"
     * @return
     */
    @GetMapping("/control")
    public List<Control> getAllControls() {
        return controlDaoUtil.findAll();
    }

    /**
     * Gets the control with the given id. Maps to HTTP get method for "/control/{id}".
     * @param id
     * @return
     */
    @GetMapping("/control/{id}")
    public Control getControl(@PathVariable UUID id) {
        return controlDaoUtil.findControl(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
    }

    /**
     * Creates a control. Maps to HTTP put method for "/control"
     * @param control The control to create.
     * @return The control that was created.
     */
    @PutMapping("/control")
    public Control createControl(Control control) {
        return controlDaoUtil.save(control);
    }

    /**
     * Deletes the control with the given id. Maps to Http delete method for "/control/{id}"
     * @param id The id of the control to delete.
     * @return The control that was deleted
     * @throws ControlNotFoundException if there was no such control.
     */
    @DeleteMapping("/control/{id}")
    public Control deleteControl(@PathVariable UUID id) {
        return controlDaoUtil.deleteById(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
    }
}
