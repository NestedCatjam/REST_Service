package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.HitrustControl;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.NistControl;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.HitrustRepository;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.NistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ControlController {
    @Autowired
    public HitrustRepository hitrustRepository;

    @Autowired
    public NistRepository nistRepository;

    @GetMapping("/api/v1/controls/{id}")
    public ResponseEntity<Object> getControl(@PathVariable long id) {
        try {
            HitrustControl hitrustControl = hitrustRepository.findById(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
            return new ResponseEntity<>(hitrustControl, HttpStatus.OK);
        } catch (ControlNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/api/v1/hitrust_control")
    public ResponseEntity<HitrustControl> createHitrustControl(@RequestBody HitrustControl hitrustControl) {
        try {
            HitrustControl savedHitrustControl = hitrustRepository.save(hitrustControl);
            return new ResponseEntity<>(savedHitrustControl, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/v1/nist_control")
    public ResponseEntity<NistControl> createNistControl(@RequestBody NistControl nistControl) {
        try {
            NistControl savedNistControl = nistRepository.save(nistControl);
            return new ResponseEntity<>(savedNistControl, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
