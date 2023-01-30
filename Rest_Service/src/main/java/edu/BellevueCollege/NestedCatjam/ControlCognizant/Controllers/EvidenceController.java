package edu.BellevueCollege.NestedCatjam.ControlCognizant.Controllers;

import edu.BellevueCollege.NestedCatjam.ControlCognizant.Entities.ComplianceEvidence;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Exceptions.ControlNotFoundException;
import edu.BellevueCollege.NestedCatjam.ControlCognizant.Repositories.EvidenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@RestController("/api/evidence")
public class EvidenceController {

    EvidenceRepository evidenceRepository;

    @Autowired
    public EvidenceController(EvidenceRepository evidenceRepository) {
        this.evidenceRepository = evidenceRepository;
    }
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
    public ComplianceEvidence createEvidence(@RequestParam String id, @RequestBody String evidence) {
        try {
            ComplianceEvidence newEvidence = new ComplianceEvidence();
            newEvidence.setId(UUID.fromString(id));
            // Write code to convert a file as a byte array somewhere around here to save to the db

            return evidenceRepository.save(newEvidence);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    @PutMapping("/evidence/{id}")
    @Transactional
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
    @Transactional
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
    private byte[] convertToByteArray(File file) throws IOException {
        FileInputStream fl = new FileInputStream(file);
        byte[] arr = new byte[(int)file.length()];
        fl.read(arr);
        fl.close();
        return arr;
    }
}
