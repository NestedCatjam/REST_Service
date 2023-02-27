package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.ControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/controls")
public class ControlController {
    @Autowired
    public ControlRepository controlRepository;

    @GetMapping
    public List<Control> getAllControls() {
        try {
            return controlRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("/{id}")
    public Control getControl(@PathVariable UUID id) {
        try {
            return controlRepository.findById(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
        } catch (ControlNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping
    public Control createControl(@RequestBody Control control) {
        try {
            return controlRepository.save(control);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PutMapping("/{id}")
    public void updateControl(@RequestBody Control control) {
        try {
            for (Control controlFromDb : controlRepository.findAll()) {
                if (controlFromDb.getId().equals(control.getId())) {
                    controlRepository.save(control);
                    return;
                }
            } throw new ControlNotFoundException("control with id " + control.getId() + " not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @DeleteMapping("/{id}")
    public void deleteControl(@PathVariable UUID id) {
        try {
            for (Control controlFromDb : controlRepository.findAll()) {
                if (controlFromDb.getId().equals(id)) {
                    controlRepository.delete(controlFromDb);
                    return;
                }
            } throw new ControlNotFoundException("control with id " + id + " not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
