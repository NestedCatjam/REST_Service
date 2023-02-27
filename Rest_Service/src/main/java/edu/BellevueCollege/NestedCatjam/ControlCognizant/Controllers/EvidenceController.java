package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Control;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.Evidence;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.EvidenceNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@RestController
public class EvidenceController {
    @Autowired
    private EvidenceRepository evidenceRepository;

    @Autowired private ControlController controlController;

    @GetMapping("/api/controls/{controlID}/evidence/")
    public List<Evidence> getEvidenceFor(@PathVariable("controlID") UUID controlID) {
        return evidenceRepository.findEvidenceByImplemented_Id(controlID);
    }

    @GetMapping("/api/controls/{controlID}/evidence/{evidenceID}")
    public Evidence getEvidenceForById(@PathVariable("controlID") UUID controlID, @PathVariable("evidenceID") long evidenceID) {
        return evidenceRepository.findEvidenceByIdAndImplemented_Id(evidenceID, controlID);
    }

    @PostMapping("/api/controls/{controlID}/evidence/")
    @Transactional
    public void postEvidenceFor(@PathVariable("controlID") UUID controlID, Authentication authentication, @RequestBody Evidence evidence) {
        final var control = controlController.getControl(controlID);
        final var contributorAuth0ID = authentication.getName();
        evidence.setContributorAuth0ID(contributorAuth0ID);
        evidence.setImplemented(control);
        evidenceRepository.save(evidence);
    }


    @GetMapping("/api/evidence")
    @ResponseBody
    public Evidence getEvidenceById(@RequestParam String id) {
        try {
            return evidenceRepository.findEvidenceById(Long.parseLong(id));
        } catch (EvidenceNotFoundException e) {
            throw new EvidenceNotFoundException("Evidence with id " + id + " not found");
        }
    }

    @PostMapping("/api/evidence")
    @Transactional
    public String postEvidence(@RequestBody Evidence evidence) {
        try {
            evidenceRepository.save(evidence);
            return "Evidence saved";
        } catch (EvidenceNotFoundException e) {
            return "Evidence not saved";
        }
    }

    @DeleteMapping("/api/evidence")
    @Transactional
    public String deleteEvidence(@RequestParam String id) {
        try {
            if (evidenceRepository.findById(Long.valueOf(id)).isPresent()) {
                evidenceRepository.deleteById(Long.valueOf(id));
                return "Evidence deleted";
            } else {
                throw new EvidenceNotFoundException("Evidence with id " + id + " not found");
            }
        } catch (EvidenceNotFoundException e) {
            return "Evidence not deleted";
        }
    }

    @PutMapping("/api/evidence")
    @Transactional
    public String updateEvidence(@RequestParam String id, @RequestBody Evidence evidence) {
        try {
            if (evidenceRepository.findById(Long.valueOf(id)).isPresent()) {
                Evidence repoEvidence = evidenceRepository.findEvidenceById(Long.parseLong(id));
                repoEvidence.setName(evidence.getName());
                repoEvidence.setDescription(evidence.getDescription());
                repoEvidence.setType(evidence.getType());
                repoEvidence.setBase64(evidence.getBase64());
                evidenceRepository.save(repoEvidence);
                return "Evidence updated";
            } else {
                throw new EvidenceNotFoundException("Evidence with id " + id + " not found");
            }
        } catch (EvidenceNotFoundException e) {
            return "Evidence not updated";
        }
    }
}
