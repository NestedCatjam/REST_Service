package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ComplianceEvidence;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.User;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao.EvidenceDaoUtil;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EvidenceController {
    @Autowired
    EvidenceRepository evidenceRepository;

    @GetMapping("/evidence/{id}")
    public ComplianceEvidence getEvidence(@PathVariable UUID id) {
        assert evidenceRepository.findById(id).isPresent();
        return evidenceRepository.findById(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
    }
    @PostMapping("/evidence")
    public void createEvidence(ComplianceEvidence evidence) {
        assert evidenceRepository.findById(evidence.getId()).isPresent();
        evidenceRepository.save(evidence);
    }
    @PutMapping("/evidence/{id}")
    public void updateEvidence(@RequestBody ComplianceEvidence evidence) {
        try {
            for (ComplianceEvidence evidenceFromDb : evidenceRepository.findAll()) {
                if (evidenceFromDb.getId().equals(evidence.getId())) {
                    evidenceRepository.save(evidence);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @DeleteMapping("/evidence/{id}")
    public void deleteEvidence(@PathVariable UUID id) {
        assert evidenceRepository.findById(id).isPresent();
        evidenceRepository.deleteById(id);
    }
}
