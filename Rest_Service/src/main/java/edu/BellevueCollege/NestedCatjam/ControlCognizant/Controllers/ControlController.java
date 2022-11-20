package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.ControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ControlController {
    @Autowired
    public ControlRepository controlRepository;

    @GetMapping("/control")
    public List<Control> getAllControls() {
        return controlRepository.findAll();
    }
    @GetMapping("/control/{id}")
    public Control getControl(@PathVariable UUID id) {
        assert controlRepository.findById(id).isPresent();
        return controlRepository.findById(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
    }
    @PostMapping("/control")
    public void createControl(@RequestBody Control control) {
        try {
            controlRepository.save(control);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @PutMapping("/control/{id}")
    public void updateControl(@RequestBody Control control) {
        try {
            for (Control controlFromDb : controlRepository.findAll()) {
                if (controlFromDb.getId().equals(control.getId())) {
                    controlRepository.save(control);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @DeleteMapping("/control/{id}")
    public void deleteControl(@PathVariable UUID id) {
        controlRepository.deleteById(id);
    }
}
