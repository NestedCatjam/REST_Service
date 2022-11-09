package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.UserNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Utils.ControlDaoUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public Control getControl(@PathVariable Long id) {
        return controlDaoUtil.findControl(id);
    }
    @PostMapping("/control")
    public Control createControl(@RequestBody Control control) {
        return controlDaoUtil.save(control);
    }
    @PutMapping("/control")
    public ResponseEntity<Object> updateControl(Long id) {
        Control updatedControl = controlDaoUtil.findControl(id);
        if(updatedControl == null) {
            throw new UserNotFoundException("id-" + id);
        }
        Control savedControl = controlDaoUtil.save(updatedControl);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedControl.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/control/{id}")
    public Control deleteControl(@PathVariable Long id) {
        return controlDaoUtil.deleteById(id);
    }
}
