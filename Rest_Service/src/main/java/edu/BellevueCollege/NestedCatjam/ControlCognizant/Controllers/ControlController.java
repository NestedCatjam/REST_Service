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
            HitrustControl hitrustControl = hitrustRepository.findById(id);
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

    @GetMapping("/api/v1/hitrust_control/{id}")
    public ResponseEntity<Object> getHitrustControl(@PathVariable long id) {
        try {
            return new ResponseEntity<>(hitrustRepository.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/hitrust_control/satisfied_requirements")
    public ResponseEntity<Object> getSatisfiedHitrustControls(Boolean satisfied) {
        try {
            return new ResponseEntity<>(hitrustRepository.findAllBySatisfied(satisfied), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/hitrust_control/{nist_mapping}")
    public ResponseEntity<Object> getHitrustControlByNistMapping(@PathVariable String nist_mapping) {
        try {
            return new ResponseEntity<>(hitrustRepository.findHitrustControlByNistMapping(nist_mapping), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/hitrust_control/{control_category}")
    public ResponseEntity<Object> getHitrustControlByControlCategory(@PathVariable String control_category) {
        try {
            return new ResponseEntity<>(hitrustRepository.findAllByControlCategory(control_category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/hitrust_control/{control_function}")
    public ResponseEntity<Object> getHitrustControlByControlFunction(@PathVariable String control_function) {
        try {
            return new ResponseEntity<>(hitrustRepository.findAllByControlFunction(control_function), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // NIST ENDPOINTS
    @GetMapping("/api/v1/nist_control/satisfied_requirements")
    public ResponseEntity<Object> getSatisfiedNistControls(Boolean satisfied) {
        try {
            return new ResponseEntity<>(nistRepository.findAllBySatisfied(satisfied), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/nist_control/{hitrust_mapping}")
    public ResponseEntity<Object> getNistControlByHitrustMapping(@PathVariable String hitrust_mapping) {
        try {
            return new ResponseEntity<>(nistRepository.findNistControlByHitrustMapping(hitrust_mapping), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/nist_control/{control_category}")
    public ResponseEntity<Object> getNistControlByControlCategory(@PathVariable String control_category) {
        try {
            return new ResponseEntity<>(nistRepository.findAllByControlCategory(control_category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/nist_control/{control_function}")
    public ResponseEntity<Object> getNistControlByControlFunction(@PathVariable String control_function) {
        try {
            return new ResponseEntity<>(nistRepository.findAllByControlFunction(control_function), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/nist_control/{control_id}")
    public ResponseEntity<Object> getNistControlByControlId(@PathVariable long control_id) {
        try {
            return new ResponseEntity<>(nistRepository.findById(control_id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
