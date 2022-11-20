package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ComplianceEvidence;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
public class EvidenceController {
    @Autowired
    EvidenceRepository evidenceRepository;

    @GetMapping("/evidence/{id}")
    public ComplianceEvidence getEvidence(@PathVariable UUID id) {
        try {
            return evidenceRepository.findById(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
        } catch (ControlNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    @PostMapping("/evidence")
    public ComplianceEvidence createEvidence(ComplianceEvidence evidence) {
        try {
            return evidenceRepository.save(evidence);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PutMapping("/evidence/{id}")
    public ComplianceEvidence updateEvidence(@RequestBody ComplianceEvidence evidence) {
        try {
            for (ComplianceEvidence evidenceFromDb : evidenceRepository.findAll()) {
                if (evidenceFromDb.getId().equals(evidence.getId())) {
                    return evidenceRepository.save(evidence);
                }
            } throw new ControlNotFoundException("control with id " + evidence.getId() + " not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @DeleteMapping("/evidence/{id}")
    public void deleteEvidence(@PathVariable UUID id) {
        try {
            for (ComplianceEvidence evidenceFromDb : evidenceRepository.findAll()) {
                if (evidenceFromDb.getId().equals(id)) {
                    evidenceRepository.delete(evidenceFromDb);
                }
            } throw new ControlNotFoundException("control with id " + id + " not found");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
