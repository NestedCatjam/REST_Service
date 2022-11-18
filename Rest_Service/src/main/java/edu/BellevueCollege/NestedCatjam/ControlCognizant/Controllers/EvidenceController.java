package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ComplianceEvidence;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Dao.EvidenceDaoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EvidenceController {
    @Autowired
    EvidenceDaoUtil evidenceDaoUtil;

    @GetMapping("/evidence")
    public List<ComplianceEvidence> getAllEvidence() {
        return evidenceDaoUtil.findAll();
    }
    @GetMapping("/evidence/{id}")
    public ComplianceEvidence getEvidence(@PathVariable UUID id) {
        return evidenceDaoUtil.findEvidence(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
    }
    @PostMapping("/evidence")
    public void createEvidence(ComplianceEvidence evidence) {
        evidenceDaoUtil.save(evidence);
    }
    @PutMapping("/evidence/{id}")
    public void updateEvidence(@PathVariable UUID id, @RequestBody ComplianceEvidence evidence) {
        evidenceDaoUtil.updateById(evidence);
    }
    @DeleteMapping("/evidence/{id}")
    public void deleteEvidence(@PathVariable UUID id) {
        evidenceDaoUtil.deleteById(id);
    }
}
