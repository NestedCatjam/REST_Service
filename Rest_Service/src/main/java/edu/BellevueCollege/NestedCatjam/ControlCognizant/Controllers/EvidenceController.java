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
    public ComplianceEvidence createEvidence(ComplianceEvidence evidence) {
        return evidenceDaoUtil.save(evidence);
    }
    @PutMapping("/evidence/{id}")
    public void updateEvidence(@PathVariable UUID id, @RequestBody ComplianceEvidence evidence) {
        evidenceDaoUtil.updateById(evidence, id);
    }
    @DeleteMapping("/evidence/{id}")
    public ComplianceEvidence deleteEvidence(@PathVariable UUID id) {
        return evidenceDaoUtil.deleteById(id).orElseThrow(() -> new ControlNotFoundException("id = " + id));
    }
}
