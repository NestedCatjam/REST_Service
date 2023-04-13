package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.ControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/controls")
public class ControlController {
    @Autowired
    public ControlRepository controlRepository;

    @GetMapping("/{id}")
    public Control getControl(@PathVariable long id) {
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
}
