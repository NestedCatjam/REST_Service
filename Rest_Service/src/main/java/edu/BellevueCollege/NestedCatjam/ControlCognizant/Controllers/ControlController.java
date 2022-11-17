package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao.ControlDaoUtil;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ControlController {
    public ControlDaoUtil controlDaoUtil;

    public ControlController(ControlDaoUtil controlDaoUtil) {
        this.controlDaoUtil = controlDaoUtil;
    }
    @GetMapping("/controls")
    public List<Control> getAllControls() {
        return controlDaoUtil.findAll();
    }
    @GetMapping("/control/{id}")
    public Control getControl(@PathVariable UUID id) {
        return controlDaoUtil.findControl(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
    }
    @PostMapping("/control")
    public void createControl(Control control) {
         controlDaoUtil.save(control);
    }
    @PutMapping("/control/{id}")
    public void updateControl(@PathVariable UUID id, Control control) {
        controlDaoUtil.updateById(id, control);
    }
    @DeleteMapping("/control/{id}")
    public void deleteControl(@PathVariable UUID id) {
        controlDaoUtil.deleteById(id);
    }
}
