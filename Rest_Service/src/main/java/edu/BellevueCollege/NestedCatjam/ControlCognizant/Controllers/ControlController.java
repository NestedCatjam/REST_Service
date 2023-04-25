package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.ControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping
public class ControlController {
    @Autowired
    public ControlRepository controlRepository;

    @GetMapping("/api/v1/controls/{id}")
    public ResponseEntity<Object> getControl(@PathVariable long id) {
        try {
            Control control = controlRepository.findById(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
            return new ResponseEntity<>(control, HttpStatus.OK);
        } catch (ControlNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/v1/controls")
    public ResponseEntity<Control> createControl(@RequestBody Control control) {
        try {
            Control savedControl = controlRepository.save(control);
            return new ResponseEntity<>(savedControl, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
